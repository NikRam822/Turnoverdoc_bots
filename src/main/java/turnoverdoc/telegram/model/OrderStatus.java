package turnoverdoc.telegram.model;

public enum OrderStatus {
    CONTACT_RECEIVED("Контакты успешно доставлены. В скором времени с вами свяжется администратор для уточнения данных", "Contacts received"),
    REQUEST_FOR_DOCS("Прикрепите указанные ниже документы поочередно", "Request for documents"),
    ALL_DOCS_RECEIVED("Ваши документы успешно доставлены. Ожидайте проверку документов администратором. В случае обнаружения ошибки с вами свяжется наш администратор", "All documents received"),
    TAX_RETURN_TO_HMRC("Ваши документы были успешно подтверждены и направлены в налоговую службу", "Tax return to HMRC"),
    CHEQUE_RECEIVED("Ваш чек был только что получен", "Cheque banked"),
    CHEQUE_BANKED("Ваш чек был успешно обналичен. Укажите ниже ваши банковские данные. Вам требуется прикрепить BIC и IBAN", "Cheque banked"),
    BANK_DETAILS_RECEIVED("Ваши банковские данные были получены, ожидайте поступления средств на счет", "Bank details received"),
    TRANSFER_MADE("Деньги были успешно переведы по вашим банковским данным. Спасибо, что выбираете нас!", "Transfer made");

    private String statusDescription;
    private String statusName;

    OrderStatus(String statusDescription, String statusName) {
        this.statusDescription = statusDescription;
        this.statusName = statusName;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String getStatusName() {
        return statusName;
    }
}
