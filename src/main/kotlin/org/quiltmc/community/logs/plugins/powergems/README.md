# PowerGems Log Processors

This directory contains log processors specifically designed to handle PowerGems plugin logs and provide helpful troubleshooting information.

## Processors

### PowerGemsDebugProcessor
Handles PowerGems debug output and configuration dumps.

**Features:**
- Detects fake debug exceptions vs real errors
- Analyzes configuration dumps from debug commands
- Provides recommendations for common configuration issues
- Checks for debug mode, gem decay settings, cooldown configurations, and disabled gems
- Creates paged embeds for configuration data

### PowerGemsCommandProcessor
Specialized processor for PowerGems command errors.

**Features:**
- Detects GiveGem command errors (index out of bounds, invalid arguments)
- Handles UpgradeGem and RemoveGem command failures
- Provides specific command syntax guidance
- Identifies missing arguments and null pointer errors
- Offers examples of correct command usage

### PowerGemsErrorProcessor
Processes PowerGems error messages and warnings.

**Features:**
- Detects plugin disable events and suggests causes
- Handles dependency errors (especially SealLib)
- Identifies database connection issues
- Processes WorldGuard integration errors
- Catches gem creation failures and configuration errors
- Handles permission-related errors

### PowerGemsPlayerProcessor
Focuses on player-related PowerGems issues and interactions.

**Features:**
- Detects attempts to use disabled gems
- Identifies excessive cooldown violations (potential spam)
- Handles corrupted/invalid gem detection
- Catches gem level limit violations
- Processes region restriction messages
- Handles permission denied issues
- Detects inventory full problems during gem distribution
- Monitors gem upgrade failures
- Tracks multiple gems violations
- Reports gem decay events

### PowerGemsPerformanceProcessor
Monitors PowerGems performance and compatibility issues.

**Features:**
- Detects performance warnings and tick lag
- Monitors memory usage issues
- Checks server version compatibility
- Identifies particle effect performance problems
- Handles effect overload situations
- Processes async operation failures
- Monitors plugin compatibility warnings
- Notifies about available updates
- Handles WorldGuard flag registration errors

## How It Works

Each processor uses regex patterns to match specific log patterns from PowerGems and provides:

1. **Problem Detection**: Identifies when something is actually wrong vs informational
2. **Helpful Messages**: Clear explanations of what the issue means
3. **Actionable Solutions**: Specific steps to resolve the problem
4. **Configuration Guidance**: Suggestions for config adjustments
5. **External Links**: Direct links to downloads or documentation when relevant

## Integration

These processors are automatically registered in the main bot application and will process any logs that contain PowerGems-related messages, providing helpful feedback to server administrators trying to troubleshoot issues.

## Supported Log Patterns

The processors handle logs from:
- PowerGems main plugin
- SealLib dependency
- PowerGems debug commands
- Configuration validation
- Player interactions
- Performance monitoring
- WorldGuard integration
- Database operations
- Update notifications
