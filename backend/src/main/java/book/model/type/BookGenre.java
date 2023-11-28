package book.model.type;

public enum BookGenre {
    ADVENTURE("冒険"),
    ECONOMIC("経済"),
    FANTASY("ファンタジー"),
    HISTORY("歴史"),
    HORROR("ホラー"),
    LIGHT_NOVEL("ライトノベル"),
    MYSTERY("ミステリー"),
    ROMANCE("恋愛"),
    SCIENCE_FICTION("SF"),
    SUSPENSE("サスペンス"),
    YOUTH("青春");

    private String label;

    private BookGenre(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}