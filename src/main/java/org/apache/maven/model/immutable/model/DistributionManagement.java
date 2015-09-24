package org.apache.maven.model.immutable.model;

public class DistributionManagement
{
    private final Site site;

    private final String downloadUrl;

    public DistributionManagement( Site site, String downloadUrl )
    {

        this.site = site;
        this.downloadUrl = downloadUrl;
    }
}
