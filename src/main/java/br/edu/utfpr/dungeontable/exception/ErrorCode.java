package br.edu.utfpr.dungeontable.exception;

public enum ErrorCode {

    // DEFAULT MESSAGES
    ID_REQUIRED("ID_REQUIRED", "O preenchimento do ID é obrigatório."),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Este e-mail já está cadastrado."),
    INVALID_PASSWORD("INVALID_PASSWORD", "A senha informada é inválida."),
    ATTRIBUTE_REQUIRED("ATTRIBUTE_REQUIRED", "Campo obrigatório não preenchido: %s"),
    FORBIDDEN_ACTION("FORBIDDEN_ACTION", "Esta ação não pode ser realizada: %s"),
    NOT_FOUND("NOT_FOUND", "Campo solicitado não encontrado: %s");

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
