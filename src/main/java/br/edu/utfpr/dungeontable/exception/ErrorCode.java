package br.edu.utfpr.dungeontable.exception;

public enum ErrorCode {

    // DEFAULT MESSAGES
    ID_REQUIRED("ID_REQUIRED", "O ID precisa ser preenchido."),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Este e-mail já está em uso."),
    INVALID_PASSWORD("INVALID_PASSWORD", "A senha informada é inválida."),
    OPERATION_NOT_ALLOWED("OPERATION_NOT_ALLOWED", "Operação não permitida."),
    ATTRIBUTE_REQUIRED("ATTRIBUTE_REQUIRED", "Campo obrigatório não preenchido: %s");

    private final String code;
    private final String messageTemplate;

    ErrorCode(String code, String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String getCode() {
        return code;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    // metodo para gerar a mensagem dinâmica
    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }
}
