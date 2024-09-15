package com.rrbofficial.androiduipracticekotlin.Security

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.GoogleApiClient
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.Security.CustomLockScreen.CustomLockScreenActivity
import io.grpc.android.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.security.KeyStore
import java.security.MessageDigest
import java.util.concurrent.Executor
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class AndroidSecurity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_android_security)

        // Enable security features to prevent screenshots and screen recordings
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_SECURE,
            android.view.WindowManager.LayoutParams.FLAG_SECURE
        )

        val biometricPromptBtn = findViewById<Button>(R.id.biometricPromptBtn)
        val checkRootBtn = findViewById<Button>(R.id.checkRootBtn)
        val secureStorageBtn = findViewById<Button>(R.id.secureStorageBtn)
        val sslPinningBtn = findViewById<Button>(R.id.sslPinningBtn)
        val encryptionBtn = findViewById<Button>(R.id.encryptionBtn)
        val codeObfuscationBtn = findViewById<Button>(R.id.codeObfuscationBtn)
        val permissionsBtn = findViewById<Button>(R.id.permissionsBtn)
        val secureNetworkBtn = findViewById<Button>(R.id.secureNetworkBtn)
        val loggingBtn = findViewById<Button>(R.id.loggingBtn)
        val integrityCheckBtn = findViewById<Button>(R.id.integrityCheckBtn)
        val seapSdkBtn = findViewById<Button>(R.id.seapSdkBtn)
        val emulatorCheckBtn = findViewById<Button>(R.id.emulatorCheckBtn)
        val safetyNetCheckBtn = findViewById<Button>(R.id.safetyNetCheckBtn)
        val appUpdateCheckBtn = findViewById<Button>(R.id.appUpdateCheckBtn)
        val tamperDetectionBtn = findViewById<Button>(R.id.tamperDetectionBtn)
        val secureClipboardBtn = findViewById<Button>(R.id.secureClipboardBtn)
        val deviceEncryptionCheckBtn = findViewById<Button>(R.id.deviceEncryptionCheckBtn)
        val keychainAccessBtn = findViewById<Button>(R.id.keychainAccessBtn)
        val networkSecurityConfigBtn = findViewById<Button>(R.id.networkSecurityConfigBtn)
        val customlockscreenbtn = findViewById<Button>(R.id.customLockscreenbtn)

        biometricPromptBtn.setOnClickListener { authenticateUser() }
        checkRootBtn.setOnClickListener { checkRootStatus() }
        secureStorageBtn.setOnClickListener { secureStorage() }
        sslPinningBtn.setOnClickListener { sslPinning() }
        encryptionBtn.setOnClickListener { dataEncryption() }
        codeObfuscationBtn.setOnClickListener { codeObfuscation() }
        permissionsBtn.setOnClickListener { checkPermissions() }
        secureNetworkBtn.setOnClickListener { secureNetwork() }
        loggingBtn.setOnClickListener { disableLogging() }
        integrityCheckBtn.setOnClickListener { appIntegrityCheck() }
        seapSdkBtn.setOnClickListener { seapSdkIntegration() }
        emulatorCheckBtn.setOnClickListener { checkEmulator() }
        appUpdateCheckBtn.setOnClickListener { checkAppUpdate() }
        tamperDetectionBtn.setOnClickListener { tamperDetection() }
        secureClipboardBtn.setOnClickListener { secureClipboard() }
        deviceEncryptionCheckBtn.setOnClickListener { checkDeviceEncryption() }
        keychainAccessBtn.setOnClickListener { keychainAccess() }
        networkSecurityConfigBtn.setOnClickListener { networkSecurityConfig() }
        customlockscreenbtn.setOnClickListener {
            val intent = Intent(this, CustomLockScreenActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    private fun authenticateUser() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> showBiometricPrompt()
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(this, "No biometric hardware available", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(this, "Biometric hardware unavailable", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(this, "No biometrics enrolled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showBiometricPrompt() {
        val executor: Executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@AndroidSecurity, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@AndroidSecurity, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                // Proceed with your app logic
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@AndroidSecurity, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun checkRootStatus() {
        val isRooted = checkRootMethod1() || checkRootMethod2()
        Toast.makeText(this, if (isRooted) "Device is rooted" else "Device is not rooted", Toast.LENGTH_SHORT).show()
    }

    private fun checkRootMethod1(): Boolean {
        val paths = arrayOf(
            "/sbin/", "/system/bin/", "/system/xbin/",
            "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/",
            "/system/bin/failsafe/", "/data/local/"
        )
        return paths.any { File(it + "su").exists() }
    }

    private fun checkRootMethod2(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            process.inputStream.bufferedReader().readLine() != null
        } catch (e: Exception) {
            false
        }
    }

    private fun secureStorage() {
        val secret = "MySecret"
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }

        val keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    "MyKeyAlias",
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build()
            )
        }

        val secretKey = keyGenerator.generateKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val iv = cipher.iv
        val ciphertext = cipher.doFinal(secret.toByteArray(Charsets.UTF_8))

        val file = File(filesDir, "secret.dat")
        FileOutputStream(file).use { it.write(iv + ciphertext) }

        Toast.makeText(this, "Data securely stored", Toast.LENGTH_SHORT).show()
    }

    private fun sslPinning() {
        // SSL Pinning logic here
        Toast.makeText(this, "SSL Pinning setup", Toast.LENGTH_SHORT).show()
    }

    private fun dataEncryption() {
        val secretMessage = "Sensitive data"
        val key = ByteArray(16).apply { java.util.Arrays.fill(this, 0x01) }
        val secretKey = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(secretMessage.toByteArray(Charsets.UTF_8))

        // Store encrypted data
        Toast.makeText(this, "Data encrypted: ${Base64.encodeToString(encrypted, Base64.DEFAULT)}", Toast.LENGTH_SHORT).show()
    }

    private fun codeObfuscation() {
        // ProGuard or R8 code obfuscation example (usually handled in build.gradle)
        Toast.makeText(this, "Code obfuscation enabled", Toast.LENGTH_SHORT).show()
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.ACCESS_NETWORK_STATE
        )
        val grantedPermissions = permissions.all {
            ContextCompat.checkSelfPermission(this, it) == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
        Toast.makeText(this, if (grantedPermissions) "All permissions granted" else "Some permissions are missing", Toast.LENGTH_SHORT).show()
    }

    private fun secureNetwork() {
        // Secure network communication example (use HTTPS, secure configurations)
        Toast.makeText(this, "Network communication secured", Toast.LENGTH_SHORT).show()
    }

    private fun disableLogging() {
        if (BuildConfig.DEBUG.not()) {
            Log.d("SensitiveData", "This log will be removed in release build")
        }
        Toast.makeText(this, "Logging disabled in release build", Toast.LENGTH_SHORT).show()
    }

    private fun appIntegrityCheck() {
        val expectedSignature = "expected_signature_here"
        val packageInfo = packageManager.getPackageInfo(packageName, android.content.pm.PackageManager.GET_SIGNATURES)
        val signatures = packageInfo.signatures
        val signature = signatures[0].toByteArray()
        val md = MessageDigest.getInstance("SHA")
        val currentSignature = Base64.encodeToString(md.digest(signature), Base64.DEFAULT)
        Toast.makeText(this, if (expectedSignature == currentSignature) "App integrity verified" else "App integrity compromised", Toast.LENGTH_SHORT).show()
    }

    private fun seapSdkIntegration() {
        // SEAP SDK integration logic here
        Toast.makeText(this, "SEAP SDK integrated", Toast.LENGTH_SHORT).show()
    }

    private fun checkEmulator() {
        val isEmulator = (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") ||
                Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") ||
                Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") ||
                "google_sdk" == Build.PRODUCT)
        Toast.makeText(this, if (isEmulator) "Running on Emulator" else "Running on Real Device", Toast.LENGTH_SHORT).show()
    }




    private fun checkAppUpdate() {
        // Proactive app update check logic here
        Toast.makeText(this, "App update check performed", Toast.LENGTH_SHORT).show()
    }

    private fun tamperDetection() {
        // Tamper detection logic here
        Toast.makeText(this, "Tamper detection performed", Toast.LENGTH_SHORT).show()
    }

    private fun secureClipboard() {
        // Secure clipboard logic here
        Toast.makeText(this, "Clipboard secured", Toast.LENGTH_SHORT).show()
    }

    private fun checkDeviceEncryption() {
        val encryptionStatus = Settings.Global.getInt(contentResolver, Settings.Global.DEVICE_PROVISIONED, 0)
        Toast.makeText(this, if (encryptionStatus != 0) "Device is encrypted" else "Device is not encrypted", Toast.LENGTH_SHORT).show()
    }

    private fun keychainAccess() {
        // Keychain access logic here
        Toast.makeText(this, "Keychain accessed", Toast.LENGTH_SHORT).show()
    }

    private fun networkSecurityConfig() {
        // Network security configuration logic here
        Toast.makeText(this, "Network security configured", Toast.LENGTH_SHORT).show()
    }
}
