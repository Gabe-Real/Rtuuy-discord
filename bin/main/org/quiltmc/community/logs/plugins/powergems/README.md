# PowerGems Log Processors

This package contains specialized log processors for handling PowerGems plugin logs and errors.

## Features

### PowerGemsDebugProcessor
- **Configuration Dumps**: Processes PowerGems configuration debug dumps with proper Discord embed formatting
- **Exception Analysis**: Detects and analyzes PowerGems-specific exceptions
- **No Truncation**: Displays complete configuration values without truncation
- **Multi-Embed Output**: Creates separate embeds for each configuration manager
- **User Guidance**: Provides actionable advice for configuration issues

### PowerGemsErrorProcessor  
- **Plugin Errors**: Handles PowerGems plugin initialization and runtime errors
- **Dependency Issues**: Detects missing or incompatible dependencies
- **Configuration Errors**: Identifies malformed configuration files
- **User Guidance**: Provides specific troubleshooting steps

### PowerGemsPlayerProcessor
- **Player Interactions**: Handles gem-related player interaction issues
- **Permission Problems**: Detects insufficient permissions for gem operations
- **Inventory Issues**: Identifies gem inventory conflicts and problems

### PowerGemsPerformanceProcessor
- **Performance Warnings**: Monitors performance-related PowerGems messages
- **Compatibility Issues**: Detects version and mod compatibility problems
- **Resource Usage**: Identifies excessive resource consumption

### PowerGemsCommandProcessor
- **Command Errors**: Handles command-specific errors (GiveGem, UpgradeGem, etc.)
- **Syntax Issues**: Detects command syntax and usage problems
- **Permission Errors**: Identifies command permission failures

## Recent Improvements

- **Enhanced Debug Processing**: Now creates individual Discord embeds for each configuration manager
- ✅ **FIXED: Discord Embed Size Limits**: Implements strict size checking to prevent "MAX_EMBED_SIZE_EXCEEDED" errors
- **Smart Truncation**: Values over 200 characters are intelligently truncated with "..." indicators
- **Embed Splitting**: Large configurations are automatically split into multiple embeds when needed
- **No Value Truncation**: Complete configuration values are displayed without cutting off (up to Discord limits)
- **Better Error Categorization**: Separate processors for different error types
- **Improved User Experience**: Clear, actionable guidance for resolving issues
- **Proper Discord Formatting**: Uses Discord embeds instead of plain text

## Technical Details

All processors use the Discord embed system (`log.embed { ... }`) for proper formatting and readability. The debug processor specifically avoids Discord's embed size limits by:

1. **Size Calculation**: Estimates embed size (title + description + fields + footer)
2. **Smart Truncation**: Truncates individual values over 200 characters with "..." indicators  
3. **Embed Splitting**: Automatically creates multiple embeds when approaching the 6000 character limit
4. **Field Chunking**: Groups settings into smaller chunks (10 per field) for better size control
5. **Buffer Management**: Maintains 500-character buffer below Discord's 6000 character limit
6. **Progress Indicators**: Shows part numbers for multi-embed configurations (e.g., "Part 1", "Part 2")

This prevents the `MAX_EMBED_SIZE_EXCEEDED` error while still providing complete configuration information.

## How It Works

Each processor uses regex patterns to match specific log patterns from PowerGems and provides:

1. **Problem Detection**: Identifies when something is actually wrong vs informational
2. **Helpful Messages**: Clear explanations of what the issue means
3. **Actionable Solutions**: Specific steps to resolve the problem
4. **Configuration Guidance**: Suggestions for config adjustments
5. **External Links**: Direct links to downloads or documentation when relevant

## Integration

These processors are automatically registered in the main bot application (`App.kt`) and will process any logs that contain PowerGems-related messages, providing helpful feedback to server administrators trying to troubleshoot issues.

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

## Future Enhancements

- Interactive navigation buttons for configuration dump browsing
- Advanced pattern matching for new error types
- Enhanced exception stack trace analysis
- Configuration validation and suggestions
