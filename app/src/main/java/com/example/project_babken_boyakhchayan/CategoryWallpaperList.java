package com.example.project_babken_boyakhchayan;

public class CategoryWallpaperList {
    private Hits[] hits;

    private String total;

    private String totalHits;

    public Hits[] getHits ()
    {
        return hits;
    }

    public void setHits (Hits[] hits)
    {
        this.hits = hits;
    }

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getTotalHits ()
    {
        return totalHits;
    }

    public void setTotalHits (String totalHits)
    {
        this.totalHits = totalHits;
    }

    @Override
    public String toString()
    {
        return "[hits = "+hits+", total = "+total+", totalHits = "+totalHits+"]";
    }
}
