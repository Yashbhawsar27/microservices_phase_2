@echo off
REM ============================================================================
REM Configuration Management - Switch to Git-based Configuration
REM ============================================================================
REM This script provides instructions to switch from local file-based
REM configuration to Git repository-based configuration management.
REM
REM Current Setup (Default):
REM - Uses local files from config-server/src/main/resources/config-files/
REM - Immediate configuration changes without Git commits
REM - Good for local development and learning
REM
REM Git-based Setup:
REM - Uses GitHub repository for configuration storage
REM - Version-controlled configuration changes
REM - Environment-specific configuration management
REM - Team collaboration on configuration changes
REM
REM Repository Details:
REM - GitHub URL: https://github.com/Yashbhawsar27/microservices_phase_2
REM - Config Path: cloud-config-server/ directory
REM - Branch: main (default)
REM
REM Benefits of Git-based Configuration:
REM - Version Control: Track all configuration changes
REM - Environment Management: Different configs for dev/test/prod
REM - Remote Updates: Update configs without service restart
REM - Team Collaboration: Shared configuration management
REM - Audit Trail: Who changed what and when
REM - Rollback Capability: Easy rollback to previous configurations
REM ============================================================================

echo Switching Config Server to use GitHub repository...

echo.
echo ========================================
echo Current Configuration Setup
echo ========================================
echo Current setup uses LOCAL config files from config-files/ directory
echo This is good for local development and learning
echo.
echo Configuration Source: Local Files
echo Location: config-server\src\main\resources\config-files\
echo Profile: native (default)
echo.
echo ========================================
echo Switch to Git-based Configuration
echo ========================================
echo.
echo To switch to GitHub repository configs:
echo.
echo 📝 Step 1: Open Configuration File
echo    File: config-server\src\main\resources\application.yml
echo.
echo 🔧 Step 2: Change Active Profile
echo    Find: spring.profiles.active: native
echo    Change to: spring.profiles.active: git
echo.
echo 🔄 Step 3: Restart Config Server
echo    Stop the config-server process
echo    Run: cd config-server ^&^& mvn spring-boot:run
echo.
echo ========================================
echo Git Repository Configuration
echo ========================================
echo.
echo 📂 GitHub Repository: https://github.com/Yashbhawsar27/microservices_phase_2
echo 📁 Config Path: cloud-config-server/
echo 🌿 Branch: main
echo 🔄 Auto-clone: Enabled (clones on startup)
echo.
echo Available Configuration Files:
echo - order-service.yml
echo - payment-service.yml  
echo - cloud-gateway.yml
echo - service-registry.yml
echo - hystrix-dashboard.yml
echo.
echo ========================================
echo Benefits of Git-based Configuration
echo ========================================
echo.
echo ✅ Version Control
echo    - Track configuration changes over time
echo    - See who made what changes and when
echo    - Complete audit trail of modifications
echo.
echo ✅ Environment Management
echo    - Different configurations for different environments
echo    - dev, test, staging, production profiles
echo    - Environment-specific property overrides
echo.
echo ✅ Remote Configuration Updates
echo    - Update configurations without redeploying services
echo    - Services can refresh configurations at runtime
echo    - Centralized configuration management
echo.
echo ✅ Team Collaboration
echo    - Multiple developers can work on configurations
echo    - Pull request workflow for configuration changes
echo    - Code review process for configuration updates
echo.
echo ✅ Rollback Capability
echo    - Easy rollback to previous working configurations
echo    - Git history provides complete change tracking
echo    - Quick recovery from configuration errors
echo.
echo ========================================
echo Configuration Refresh (Advanced)
echo ========================================
echo.
echo To refresh configuration without restart:
echo 1. Make changes in GitHub repository
echo 2. POST to /actuator/refresh endpoint of each service
echo 3. Services will fetch updated configuration
echo.
echo Example:
echo curl -X POST http://localhost:8081/actuator/refresh
echo curl -X POST http://localhost:8082/actuator/refresh
echo.
echo Note: Requires @RefreshScope annotation on configuration classes
echo.
echo ========================================
echo Troubleshooting
echo ========================================
echo.
echo If Git configuration doesn't work:
echo 1. Check internet connectivity
echo 2. Verify GitHub repository URL
echo 3. Check config-server logs for errors
echo 4. Ensure cloud-config-server/ directory exists in repo
echo 5. Switch back to 'native' profile if needed
echo.
pause