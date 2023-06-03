package projectpolaris.Configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class JWTConfiguration {

    private String secret  =
            "sanman_de_fuantei_na_kono_sekai"                +
            "kakumei_ho_okoshite_yo"                         +
            "_"                                              +
            "'AI'_ga_seikai_ni_nare"                         +
            "_"                                              +
            "fuantei_nantei_kankan_ni_kiechate"              +
            "hakumei_no_hi_terasu_sono_mukou_he"             +
            "_"                                              +
            "kiroku_mo_kioku_mo_nai_hito_maishou"            +
            "kibuo_mo_mirai_mo_nai_kuni_gaishou"             +
            "_"                                              +
            "nagai_yoru_wo_oe_asayake_wo_mita"               +
            "akaku_somaru_senaka_wooo"                       +
            "_"                                              +
            "NEE_ANATA_WA_ITSUKA_INAKU_NARI_MASU_KA?"        +
            "OWARI_KAKE_NO_KONO_SEKAI_DE"                    +
            "YAOKE_MAE_NO_UREI_TAEGATAI_KODOKU"              +
            "AWAI_OMOI_TO_SEKIBAKU_WO_DAITA"                 +
            "_"                                              +
            "senmen_na_dangan_no_kioku_ni"                   +
            "dare_mo_ga_hi_yotte_wa"                         +
            "_"                                              +
            "DAIPO_GA_DE_NAI"                                +
            "_"                                              +
            "anzen_wa_kankan_ni_kiechate"                    +
            "akukeyou_oyoboshita_boku_no_kokoro_he"          +
            "_"                                              +
            "nagai_ame_ga_yami_atsui_hizashi_ga"             +
            "terasu_ookina_senaka_tooo"                     +
            "_"                                            +
            "NEE_ANATA_WA_ITSUMO_YUME_WO_MITE_MASU_KA_?"  +
            "OWARI_KAKE_NO_KONO_SEKAI_DE"                +
            "FUSAGA_RE_TA_MICCHI_WO_HITAKU_MONO_NI_NARU" +
            "'MESSEJI'_WO_IDA_ITA_TSURAI_YOOOO";

    //one hour
    private final long EXPIRATION_TIME = 3600_000;

    //Delimiter of the token
    private final String TOKEN_PREFIX = "Bearer_";

    //Does not have much of use but might be useful later
    private final String HEADER_STRING = "Authorization";
}
