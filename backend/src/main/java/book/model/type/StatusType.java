package book.model.type;

public enum StatusType {
    /** 有効 */
    VALID,
    /** 無効 */
    INVALID;

    public Boolean isValid() {
        return this == VALID;
    }
}
