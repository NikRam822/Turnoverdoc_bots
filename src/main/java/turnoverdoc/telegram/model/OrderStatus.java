package turnoverdoc.telegram.model;

public enum OrderStatus {
    CONTACT_RECEIVED,
    REQUEST_FOR_DOCS,
    ALL_DOCS_RECEIVED,
    TAX_RETURN_TO_HMRC,
    CHEQUE_RECEIVED,
    CHEQUE_BANKED,
    BANK_DETAILS_RECEIVED,
    TRANSFER_MADE;
}
