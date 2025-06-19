/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.quiltmc.community.logs.plugins.powergems

import org.quiltmc.community.cozy.modules.logs.data.Log
import org.quiltmc.community.cozy.modules.logs.data.Order
import org.quiltmc.community.cozy.modules.logs.types.LogProcessor

private val POWERGEMS_GIVE_GEM_ERROR_REGEX =
	"""\[SealUtils] Exception triggered by dev\.iseal\.powergems\.commands\.GiveGemCommand""".toRegex()

private val POWERGEMS_INDEX_OUT_OF_BOUNDS_REGEX =
	"""\[SealUtils] The exception message is Index (\d+) out of bounds for length (\d+)""".toRegex()

private val POWERGEMS_UPGRADE_GEM_ERROR_REGEX =
	"""\[SealUtils] Exception triggered by dev\.iseal\.powergems\.commands\.UpgradeGemCommand""".toRegex()

private val POWERGEMS_REMOVE_GEM_ERROR_REGEX =
	"""\[SealUtils] Exception triggered by dev\.iseal\.powergems\.commands\.RemoveGemCommand""".toRegex()

private val POWERGEMS_NULL_POINTER_REGEX =
	"""\[SealUtils] The exception message is (Cannot invoke .+ because .+ is null)""".toRegex()

private val POWERGEMS_INVALID_ARGUMENT_REGEX =
	"""\[SealUtils] The exception message is (Invalid .+)""".toRegex()

public class PowerGemsCommandProcessor : LogProcessor() {
	override val identifier: String = "powergems_command_processor"
	override val order: Order = Order.Earlier

	override suspend fun process(log: Log) {
		val giveGemError = POWERGEMS_GIVE_GEM_ERROR_REGEX.find(log.content)
		val upgradeGemError = POWERGEMS_UPGRADE_GEM_ERROR_REGEX.find(log.content)
		val removeGemError = POWERGEMS_REMOVE_GEM_ERROR_REGEX.find(log.content)
		val indexOutOfBounds = POWERGEMS_INDEX_OUT_OF_BOUNDS_REGEX.find(log.content)
		val nullPointerError = POWERGEMS_NULL_POINTER_REGEX.find(log.content)
		val invalidArgument = POWERGEMS_INVALID_ARGUMENT_REGEX.find(log.content)

		// Handle GiveGem command errors
		if (giveGemError != null) {
			var message = "**PowerGems GiveGem Command Error** \n"
			
			if (indexOutOfBounds != null) {
				val index = indexOutOfBounds.groupValues[1]
				val length = indexOutOfBounds.groupValues[2]
				message += "Index out of bounds error: trying to access argument $index when only $length arguments provided\n\n"
				message += "**How to fix:**\n"
				message += "• Use correct syntax: `/givegem <player> <gem_type> [level]`\n"
				message += "• Required: player name and gem type\n"
				message += "• Optional: gem level (defaults to 1)\n"
				message += "• Valid gem types: Air, Fire, Healing, Ice, Iron, Lava, Lightning, Sand, Strength, Water\n\n"
				message += "**Example:** `/givegem PlayerName Fire 3`"
			} else if (nullPointerError != null) {
				message += "Null pointer error: ${nullPointerError.groupValues[1]}\n\n"
				message += "• Check that the player exists and is online\n"
				message += "• Verify the gem type is spelled correctly\n"
				message += "• Ensure PowerGems is fully initialized"
			} else {
				message += "Unknown error in GiveGem command\n"
				message += "• Check command syntax: `/givegem <player> <gem_type> [level]`\n"
				message += "• Verify all arguments are provided correctly"
			}
			
			log.addMessage(message)
			log.hasProblems = true
		}

		// Handle UpgradeGem command errors
		if (upgradeGemError != null) {
			var message = "**PowerGems UpgradeGem Command Error** \n"
			
			if (indexOutOfBounds != null) {
				message += "Missing required arguments for upgrade command\n\n"
				message += "**How to fix:**\n"
				message += "• Use correct syntax: `/upgradegem <player> [gem_type]`\n"
				message += "• Player name is required\n"
				message += "• Gem type is optional (upgrades held gem if not specified)"
			} else if (nullPointerError != null) {
				message += "Player or gem not found\n\n"
				message += "• Ensure the player is online and has a gem\n"
				message += "• Check that the gem type exists and is enabled"
			}
			
			log.addMessage(message)
			log.hasProblems = true
		}

		// Handle RemoveGem command errors
		if (removeGemError != null) {
			var message = "**PowerGems RemoveGem Command Error** \n"
			
			if (indexOutOfBounds != null) {
				message += "Missing required arguments for remove command\n\n"
				message += "**How to fix:**\n"
				message += "• Use correct syntax: `/removegem <player> [gem_type]`\n"
				message += "• Player name is required\n"
				message += "• Gem type is optional (removes all gems if not specified)"
			}
			
			log.addMessage(message)
			log.hasProblems = true
		}

		// Handle general invalid argument errors
		if (invalidArgument != null && (giveGemError != null || upgradeGemError != null || removeGemError != null)) {
			log.addMessage(
				"**PowerGems Invalid Argument** \n" +
					"${invalidArgument.groupValues[1]}\n\n" +
					"• Double-check spelling of gem types and player names\n" +
					"• Gem types are case-sensitive\n" +
					"• Use tab completion to avoid typos"
			)
			log.hasProblems = true
		}
	}
}
