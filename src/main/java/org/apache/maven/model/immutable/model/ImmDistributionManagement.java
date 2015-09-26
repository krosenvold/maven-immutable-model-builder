package org.apache.maven.model.immutable.model;

import org.apache.maven.model.DistributionManagement;

public class ImmDistributionManagement
{
    private final ImmSite site;

    private final String downloadUrl;

    public ImmDistributionManagement( ImmSite site, String downloadUrl )
    {

        this.site = site;
        this.downloadUrl = downloadUrl;
    }

    public DistributionManagement toDistributionManagement()
    {
        DistributionManagement result = new DistributionManagement();
        result.setDownloadUrl( downloadUrl );
        result.setSite( site.toSite() );
        // result.setRelocation(  ); TODO
        // result.setRepository(  ); TODO
        // result.setSnapshotRepository(  ); TODO
        return result;
    }
}
