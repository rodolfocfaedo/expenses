// src/main/java/io/github/faedorodolfo/learning_spring_boot/infrastructure/security/util/JwtKeyGeneratorUtil.java
package com.rodolfocf.expenses_api.infrastructure.security;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Utilitário para gerar uma chave segura HMAC-SHA256 (256 bits) e
 * configurá-la como variável de ambiente no Windows, se ainda não existir.
 */
public class JwtKeyGeneratorUtil {

    private static final Logger logger = Logger.getLogger(JwtKeyGeneratorUtil.class.getName());

    /**
     * Gera uma chave JWT segura de 256 bits e a define como variável de ambiente no Windows.
     * Executa apenas se a variável JWT_SECRET_KEY ainda não estiver definida.
     */
    public static void setupJwtKeyIfMissing() {
        String existingKey = System.getenv("JWT_SECRET_KEY");
        if (existingKey != null && !existingKey.trim().isEmpty()) {
            logger.info("Chave JWT já existe no ambiente. Usando existente.");
            return;
        }

        try {
            String newKey = generateSecureKey();
            setEnvironmentVariable("JWT_SECRET_KEY", newKey);
            logger.info("✅ Chave JWT gerada e salva no ambiente do Windows.");
            logger.info("🔑 Chave (Base64): " + newKey);
        } catch (Exception e) {
            logger.severe("❌ Falha ao gerar ou salvar a chave JWT: " + e.getMessage());
            throw new RuntimeException("Erro ao configurar chave JWT", e);
        }
    }

    /**
     * Gera uma chave criptograficamente segura de 256 bits usando HMAC-SHA256.
     *
     * @return chave codificada em Base64
     */
    private static String generateSecureKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256); // 256 bits
        byte[] secretKey = keyGen.generateKey().getEncoded();
        return Base64.getEncoder().encodeToString(secretKey);
    }

    /**
     * Define uma variável de ambiente no Windows usando PowerShell.
     *
     * @param name  nome da variável
     * @param value valor da variável
     */
    private static void setEnvironmentVariable(String name, String value) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                "powershell", "-Command",
                String.format("setx %s \"%s\"", name, value)
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Lê saída para depuração
            process.getInputStream().transferTo(System.out);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("PowerShell retornou erro: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao definir variável de ambiente no Windows", e);
        }
    }
}