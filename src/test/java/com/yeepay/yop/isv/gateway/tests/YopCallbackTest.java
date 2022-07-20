/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.gateway.tests;

import com.yeepay.yop.isv.gateway.WebApplicationTest;
import com.yeepay.yop.sdk.utils.JsonUtils;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/8/19 14:25
 */
public class YopCallbackTest extends WebApplicationTest {

    @Test
    public void notifyRsaSuccess() throws Exception {
        // app_Fe51qCyZWcEnDMtK
        // {"date":"20181014000000","aaa":"","boolean":true,"SIZE":-14,"name":"易宝支付","dou":12.134}
        // 随机对称密钥：kLNl5Q7MOV0FRw_dl1VDPg
        // 签名：egc-j7UPBntdMMaz5P08MhVkkpOl8RbbBKzfAgQeNoDTbOG8RYxIlVPu1rIaZnSwc7J6ZQB-gYSDatzQ4tZlHOXRqVcuZiKX0qU2CjdSUscWOLkL95g6wFAMTCNCK0OmHEj8oldCrlD1F0-_klVRFPiVn4kUAnJ8GggAn05nP1PDeV3ZFsVxGUTA4aGHVZwdRMCkcbxhnAyJRPcWPmjkMfXUrp5a76s8iHd9-SCdeA-I5AXcr0TVp9ELACPqG-K-u7wTAaQvf5c2yWIjkqHsTygXKaDEmY9MDJJFqANDbI6D5F5_m2vV_rrUYtAMbHQ6ivhgBOyrYWm0COfR-n1F1g
        // 信封：ZIcrArlonH0mxIWCRejL2VQS2qeK5EFz2gALzdMbusIU8eqwnWNgJRWiTwJElSQEhnT42KkU3jXWZr2dd0A8-bZSjT-hvNCUI0aoJZkadRtJrWoe_ygGhOLegj7cTbk8y7GOzfFQteIFbB9ALae1CqWVHgfgyozbTLgsse4MfuYjio9r3DOkCJJSkW6mEHB0G4rTXSWFni0h_Uhtu5jsuCTU4vWDPKrBIZI17rr1AIqmyOd8C8oLCAplC1JT4KnLq5QCir4cnvZJrGYB5-bI00gPOdGX2_v4Az3VqMkh8PqqPSDriJ-PqDo9T2dnjR5njYkTSSzUpIXg6cfhLaTNIQ$0sn1fsX0zRYXv-bb0tV531Brbhb-fPORrXYqe8JzHbnL8NkAwIPRkSaTXfq3etJnmslkkBlPpZeTc7639TWQlBrl3eVW-aQKIjFX4bhfyythIh5ByjBAHw1RaYwoHw10kkpbBBk01K-6pzE9QzT6TvjZLsSsXZ6O3WJdvrB8dtpJA-PI-sOzm7DXkBqfKOSufkN1C1mRvexBlcN3ScSH2TKo5ZwKw3Fo_93GsYFD0hzYmHpC6yCyHXeY1PPlHYqd_KsqXVo_xBtXMCadoKnldYnMljXdhAQJLRdlkwTgeD8FX18SQSJ18O6Ag0w3IM9QXkcgZVgIo1-_ZUncc5AtNXyQCvfT4tNyaIRFsXlFqj5tCc5bekMz8OzYeRTPfmfCLKXmjvg4ICMw0aIRboX1tyZpCHdHU269u0-wX90pMDNRqBZsLag6glNDSzEG8RQaB4vGrjvxYy0ixeUnogwni2qqnnGX5Gfhkst7FPYubAsi5HweDT_aJIrmE6kMiBrpMAOcIGZ6slYK854FOH3ODO9-raz7n2P__NUTpziTF4t4Jru_erJevVoGyHH81qq_msIMvK7IRx2z1QoExRL08A$AES$SHA256
        String content = "{\"response\":\"ZIcrArlonH0mxIWCRejL2VQS2qeK5EFz2gALzdMbusIU8eqwnWNgJRWiTwJElSQEhnT42KkU3jXWZr2dd0A8-bZSjT-hvNCUI0aoJZkadRtJrWoe_ygGhOLegj7cTbk8y7GOzfFQteIFbB9ALae1CqWVHgfgyozbTLgsse4MfuYjio9r3DOkCJJSkW6mEHB0G4rTXSWFni0h_Uhtu5jsuCTU4vWDPKrBIZI17rr1AIqmyOd8C8oLCAplC1JT4KnLq5QCir4cnvZJrGYB5-bI00gPOdGX2_v4Az3VqMkh8PqqPSDriJ-PqDo9T2dnjR5njYkTSSzUpIXg6cfhLaTNIQ$0sn1fsX0zRYXv-bb0tV531Brbhb-fPORrXYqe8JzHbnL8NkAwIPRkSaTXfq3etJnmslkkBlPpZeTc7639TWQlBrl3eVW-aQKIjFX4bhfyythIh5ByjBAHw1RaYwoHw10kkpbBBk01K-6pzE9QzT6TvjZLsSsXZ6O3WJdvrB8dtpJA-PI-sOzm7DXkBqfKOSufkN1C1mRvexBlcN3ScSH2TKo5ZwKw3Fo_93GsYFD0hzYmHpC6yCyHXeY1PPlHYqd_KsqXVo_xBtXMCadoKnldYnMljXdhAQJLRdlkwTgeD8FX18SQSJ18O6Ag0w3IM9QXkcgZVgIo1-_ZUncc5AtNXyQCvfT4tNyaIRFsXlFqj5tCc5bekMz8OzYeRTPfmfCLKXmjvg4ICMw0aIRboX1tyZpCHdHU269u0-wX90pMDNRqBZsLag6glNDSzEG8RQaB4vGrjvxYy0ixeUnogwni2qqnnGX5Gfhkst7FPYubAsi5HweDT_aJIrmE6kMiBrpMAOcIGZ6slYK854FOH3ODO9-raz7n2P__NUTpziTF4t4Jru_erJevVoGyHH81qq_msIMvK7IRx2z1QoExRL08A$AES$SHA256\",\"customerIdentification\":\"app_Fe51qCyZWcEnDMtK\"}";

        webClient.post().uri("/yop-callback/rest/v1.0/test/app-alias/create").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("SUCCESS");
        });
    }

    @Test
    public void notifySm4Success() throws Exception {
        String content = "{\"cipherText\":\"2MV2vk_W8NlpHxB3L8wdiBXVALVF13aNxmbwILLQiSNdWYSXN_aM2YZk1n-oPMdW-heEt9_cIyNb\",\"customerIdentification\":\"app_15958159879157110009\",\"algorithm\":\"AEAD_SM4_GCM\"}";

        webClient.post().uri("/yop-callback/rest/v1.0/test/app-alias/create").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("SUCCESS");
        });
    }

    @Test
    public void notifySm2Success() throws Exception {
        String content = JsonUtils.toJsonString("EZgjreIx_ZW-gIM2NtHoKSk2sMQ35eolEjZ76XPcCtEqbXRfv77Z2eUJHhfoN4TcAZjPykzzDJ2pH7FC8xbhXw");

        webClient.post().uri("/yop-callback/rest/v1.0/test/app-alias/create").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "YOP-SM2-SM3 yop-auth-v3/app_15958159879157110009/2022-05-17T02:33:24Z/1800/content-length;content-type;x-yop-appkey;x-yop-content-sm3;x-yop-encrypt;x-yop-request-id/fuKri2WjLqmr_gKInxstDLn6zz9XPR518TKK2iF9sMROSEcWllrAxApO4ldPrjNPPc0UsAbitCxnumA3-CJt8A$SM3")
                .header("x-yop-content-sm3", "eaa5391d992058fce198590bcfb7f7a4533d8ea311ac97c964513d7da080351f")
                .header("x-yop-encrypt", "yop-encrypt-v1/4029287836/SM4_CBC_PKCS5Padding/BEmuYglu6Y0M5jkqZN_yssw137rWIiaB0ToXJXsQytFDSwau5sMGnPKCnEe-2Bgg_ThowDqOdcGnsvzATS4ol4rk_fSPebBPMvnjyWZk5hpMYPJxCCEJ80MgHYE3pBt50LulUCaCYhYDyf4VO5rYyjQ/u3E2PbLDjeiZi9IeQm7xyA/stream//JA")
                .header("x-yop-request-id", "wuTest1652754804319")
                .header("x-yop-sign-serial-no", "4029287836")
                .header("x-yop-appkey", "app_15958159879157110009")
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("{\"result\":\"SUCCESS\"}");
        });
    }

    @Test
    public void notifyUnHandler() throws Exception {
        String content = "{\"response\":\"ZIcrArlonH0mxIWCRejL2VQS2qeK5EFz2gALzdMbusIU8eqwnWNgJRWiTwJElSQEhnT42KkU3jXWZr2dd0A8-bZSjT-hvNCUI0aoJZkadRtJrWoe_ygGhOLegj7cTbk8y7GOzfFQteIFbB9ALae1CqWVHgfgyozbTLgsse4MfuYjio9r3DOkCJJSkW6mEHB0G4rTXSWFni0h_Uhtu5jsuCTU4vWDPKrBIZI17rr1AIqmyOd8C8oLCAplC1JT4KnLq5QCir4cnvZJrGYB5-bI00gPOdGX2_v4Az3VqMkh8PqqPSDriJ-PqDo9T2dnjR5njYkTSSzUpIXg6cfhLaTNIQ$0sn1fsX0zRYXv-bb0tV531Brbhb-fPORrXYqe8JzHbnL8NkAwIPRkSaTXfq3etJnmslkkBlPpZeTc7639TWQlBrl3eVW-aQKIjFX4bhfyythIh5ByjBAHw1RaYwoHw10kkpbBBk01K-6pzE9QzT6TvjZLsSsXZ6O3WJdvrB8dtpJA-PI-sOzm7DXkBqfKOSufkN1C1mRvexBlcN3ScSH2TKo5ZwKw3Fo_93GsYFD0hzYmHpC6yCyHXeY1PPlHYqd_KsqXVo_xBtXMCadoKnldYnMljXdhAQJLRdlkwTgeD8FX18SQSJ18O6Ag0w3IM9QXkcgZVgIo1-_ZUncc5AtNXyQCvfT4tNyaIRFsXlFqj5tCc5bekMz8OzYeRTPfmfCLKXmjvg4ICMw0aIRboX1tyZpCHdHU269u0-wX90pMDNRqBZsLag6glNDSzEG8RQaB4vGrjvxYy0ixeUnogwni2qqnnGX5Gfhkst7FPYubAsi5HweDT_aJIrmE6kMiBrpMAOcIGZ6slYK854FOH3ODO9-raz7n2P__NUTpziTF4t4Jru_erJevVoGyHH81qq_msIMvK7IRx2z1QoExRL08A$AES$SHA256\",\"customerIdentification\":\"app_Fe51qCyZWcEnDMtK\"}";

        webClient.post().uri("/yop-callback/payment").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("FAIL, cause:no YopCallbackHandler found");
        });
    }

}
