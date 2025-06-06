/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
	}
}

rootProject.name = "RtuuyDiscord"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include(":module-log-parser")

