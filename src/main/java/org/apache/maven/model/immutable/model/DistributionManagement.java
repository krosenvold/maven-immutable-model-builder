package org.apache.maven.model.immutable.model;

public class DistributionManagement
{
    private final ImmSite site;

    private final String downloadUrl;

    public DistributionManagement( ImmSite site, String downloadUrl )
    {

        this.site = site;
        this.downloadUrl = downloadUrl;
    }
}
