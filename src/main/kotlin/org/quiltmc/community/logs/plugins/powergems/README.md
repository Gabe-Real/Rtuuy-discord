# PowerGems Log Processors

This package contains specialized log processors for handling PowerGems plugin logs and errors.

## Features

### PowerGemsDebugProcessor
- **Configuration Dumps**: Processes PowerGems configuration debug dumps with proper Discord embed formatting
- **Exception Analysis**: Detects and analyzes PowerGems-specific exceptions
- **Safe Display**: Uses ultra-conservative limits to prevent Discord embed size errors
- **Multi-Embed Output**: Creates separate embeds for each configuration manager with automatic splitting
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
- ✅ **FIXED: Discord Embed Size Limits**: Implements ultra-conservative approach to prevent "MAX_EMBED_SIZE_EXCEEDED" errors
- **Ultra-Safe Chunking**: Maximum 3 configuration entries per embed to guarantee size compliance
- **Smart Truncation**: Values over 100 characters are intelligently truncated with "..." indicators
- **Automatic Splitting**: Large configurations are automatically split into multiple embeds
- **Better Error Categorization**: Separate processors for different error types
- **Improved User Experience**: Clear, actionable guidance for resolving issues
- **Proper Discord Formatting**: Uses Discord embeds instead of plain text

## Technical Details

All processors use the Discord embed system (`log.embed { ... }`) for proper formatting and readability. The debug processor uses an ultra-conservative approach to prevent Discord embed size limit errors:

1. **Ultra-Safe Chunking**: Maximum 3 configuration entries per embed (well below Discord's limits)
2. **Conservative Truncation**: Values truncated at 100 characters instead of 200+ 
3. **Field Limits**: Field values capped at 800 characters (well below Discord's 1024 limit)
4. **Field Names**: Truncated to 200 characters for safety
5. **Automatic Splitting**: Creates multiple embeds with clear part indicators
6. **Zero Size Calculation**: No complex size estimation - uses fixed safe limits instead

This **guaranteed approach** prevents the `MAX_EMBED_SIZE_EXCEEDED` error by staying well within all Discord limits, even for the largest PowerGems configuration dumps.

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
