package com.seanproctor.onvifcamera

object OnvifCommands {
    /**
     * The header for SOAP 1.2 with digest authentication
     */
    private const val soapHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<soap:Envelope " +
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
            "xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" >" +
            "<soap:Body>"

    private const val envelopeEnd = "</soap:Body></soap:Envelope>"

    const val profilesCommand = (
            soapHeader
                    + "<GetProfiles xmlns=\"http://www.onvif.org/ver10/media/wsdl\"/>"
                    + envelopeEnd
            )

    fun getStreamURICommand(profile: MediaProfile, protocol: String = "RTSP"): String {
        return (soapHeader
                + "<GetStreamUri xmlns=\"http://www.onvif.org/ver20/media/wsdl\">"
                + "<ProfileToken>${profile.token}</ProfileToken>"
                + "<Protocol>${protocol}</Protocol>"
                + "</GetStreamUri>"
                + envelopeEnd
                )
    }

    fun getSnapshotURICommand(profile: MediaProfile): String {

        return (soapHeader + "<GetSnapshotUri xmlns=\"http://www.onvif.org/ver20/media/wsdl\">"
                + "<ProfileToken>${profile.token}</ProfileToken>"
                + "</GetSnapshotUri>" + envelopeEnd)
    }

    const val deviceInformationCommand = (
            soapHeader
                    + "<GetDeviceInformation xmlns=\"http://www.onvif.org/ver10/device/wsdl\">"
                    + "</GetDeviceInformation>"
                    + envelopeEnd
            )

    const val servicesCommand = (
            soapHeader
                    + "<GetServices xmlns=\"http://www.onvif.org/ver10/device/wsdl\">"
                    + "<IncludeCapability>false</IncludeCapability>"
                    + "</GetServices>"
                    + envelopeEnd
            )
}