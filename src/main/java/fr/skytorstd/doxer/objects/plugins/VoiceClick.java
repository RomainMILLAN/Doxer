package fr.skytorstd.doxer.objects.plugins;

public class VoiceClick {

    private int vc_id;
    private String vc_name;
    private String vc_channel_id;

    public VoiceClick(int vc_id, String vc_name, String vc_channel_id) {
        this.vc_id = vc_id;
        this.vc_name = vc_name;
        this.vc_channel_id = vc_channel_id;
    }

    public VoiceClick(String vc_name, String vc_channel_id) {
        this.vc_name = vc_name;
        this.vc_channel_id = vc_channel_id;
    }

    public int getVc_id() {
        return vc_id;
    }

    public String getVc_name() {
        return vc_name;
    }

    public String getVc_channel_id() {
        return vc_channel_id;
    }
}
