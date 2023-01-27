public class Track {
    private String selectedImg;
    private String gameMusic;
    private String titleName;

    public String getTitleImg() {
        return selectedImg;
    }

    public void setTitleImg(String selectedImg) {
        this.selectedImg = selectedImg;
    }

    public String getGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(String gameMusic) {
        this.gameMusic = gameMusic;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Track(String selectedImg, String gameMusic, String titleName) {
        this.selectedImg = selectedImg;
        this.gameMusic = gameMusic;
        this.titleName = titleName;
    }

}
