/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.isv.event.gateway;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.yeepay.yop.isv.event.sdk.constant.YopConstant.*;
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
public class YopEventTest extends IntegrationTests {

    private static final String appId = "app_Fe51qCyZWcEnDMtK";

    // TODO 待测试

    @Ignore
    @Test
    public void eventSuccess() throws Exception {
//        x-yop-appkey:app_Fe51qCyZWcEnDMtK
//        x-yop-date:20201112T120419Z
//        x-yop-event-type:
//        x-yop-protocol:YOP%23V2
//        x-yop-request-id:1a8f15e490744fcfbaffa6b6a6fb61b6
//        {"date":"2018-10-14 00:00:00","aaa":"","boolean":true,"SIZE":-14,"name":"易宝支付","dou":12.134}
        String content = "x-yop-appkey:app_Fe51qCyZWcEnDMtK\n" +
                "x-yop-date:20201112T120419Z\n" +
                "x-yop-event-type:\n" +
                "x-yop-protocol:YOP%23V2\n" +
                "x-yop-request-id:1a8f15e490744fcfbaffa6b6a6fb61b6\n" +
                "{\"date\":\"2018-10-14 00:00:00\",\"aaa\":\"\",\"boolean\":true,\"SIZE\":-14,\"name\":\"易宝支付\",\"dou\":12.134}";

        webClient.post().uri("/yop-event").contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_REQUEST_ID, RandomStringUtils.randomAlphanumeric(20))
                .header(HEADER_APP_ID, appId)
                .header(HEADER_EVENT_TYPE, "yop.payment")
                .header(HEADER_SIGN, "4AA36eTwsWAzsr8-cFKcUHasB_Zer_AOw_SxxtnCWqci_4tWqE_9mYwdAKXDxHUpa0GiAB183c8VuWu6ZsFxdFNpE5d8N_WwlZe7X0Gi34a-rZs-gX3ktnrbdGxnaqwPRiW-XU5X49rrLJZWZQI2CF9GJF2DzYL-7-9MwavZaAKgZH0iRT4RIHwMKHTiUsVtMnsHo37GI6AN1KimD0d9qaSBYValBNdJZPPk7FhLO82HH0YzJgdeQpcv1QXTz4-zBlXgIhxE6thhuOmeFqLCcSb9WJhM_clcVdubNWh2adxEWSW5P0vCeYuqaNrAlb07jCxnAQ-kVgedmxIn1ODjcw")
                .header(HEADER_SIGN_TYPE, "RSA2048")
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("Success");
        });
    }

    @Ignore
    @Test
    public void eventUnHandler() throws Exception {
        String content = "{\"date\":\"20181014000000\",\"aaa\":\"\",\"boolean\":true,\"SIZE\":-14,\"name\":\"易宝支付\",\"dou\":12.134}";

        webClient.post().uri("/yop-event").contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_REQUEST_ID, RandomStringUtils.randomAlphanumeric(20))
                .header(HEADER_APP_ID, appId)
                .header(HEADER_EVENT_TYPE, "yop.payment")
                .header(HEADER_SIGN, "4AA36eTwsWAzsr8-cFKcUHasB_Zer_AOw_SxxtnCWqci_4tWqE_9mYwdAKXDxHUpa0GiAB183c8VuWu6ZsFxdFNpE5d8N_WwlZe7X0Gi34a-rZs-gX3ktnrbdGxnaqwPRiW-XU5X49rrLJZWZQI2CF9GJF2DzYL-7-9MwavZaAKgZH0iRT4RIHwMKHTiUsVtMnsHo37GI6AN1KimD0d9qaSBYValBNdJZPPk7FhLO82HH0YzJgdeQpcv1QXTz4-zBlXgIhxE6thhuOmeFqLCcSb9WJhM_clcVdubNWh2adxEWSW5P0vCeYuqaNrAlb07jCxnAQ-kVgedmxIn1ODjcw")
                .header(HEADER_SIGN_TYPE, "RSA2048")
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("Fail");
        });
    }

    @Test
    public void notifySuccess() throws Exception {
        // app_Fe51qCyZWcEnDMtK
        // {"date":"20181014000000","aaa":"","boolean":true,"SIZE":-14,"name":"易宝支付","dou":12.134}
        // 随机对称密钥：kLNl5Q7MOV0FRw_dl1VDPg
        // 签名：egc-j7UPBntdMMaz5P08MhVkkpOl8RbbBKzfAgQeNoDTbOG8RYxIlVPu1rIaZnSwc7J6ZQB-gYSDatzQ4tZlHOXRqVcuZiKX0qU2CjdSUscWOLkL95g6wFAMTCNCK0OmHEj8oldCrlD1F0-_klVRFPiVn4kUAnJ8GggAn05nP1PDeV3ZFsVxGUTA4aGHVZwdRMCkcbxhnAyJRPcWPmjkMfXUrp5a76s8iHd9-SCdeA-I5AXcr0TVp9ELACPqG-K-u7wTAaQvf5c2yWIjkqHsTygXKaDEmY9MDJJFqANDbI6D5F5_m2vV_rrUYtAMbHQ6ivhgBOyrYWm0COfR-n1F1g
        // 信封：ZIcrArlonH0mxIWCRejL2VQS2qeK5EFz2gALzdMbusIU8eqwnWNgJRWiTwJElSQEhnT42KkU3jXWZr2dd0A8-bZSjT-hvNCUI0aoJZkadRtJrWoe_ygGhOLegj7cTbk8y7GOzfFQteIFbB9ALae1CqWVHgfgyozbTLgsse4MfuYjio9r3DOkCJJSkW6mEHB0G4rTXSWFni0h_Uhtu5jsuCTU4vWDPKrBIZI17rr1AIqmyOd8C8oLCAplC1JT4KnLq5QCir4cnvZJrGYB5-bI00gPOdGX2_v4Az3VqMkh8PqqPSDriJ-PqDo9T2dnjR5njYkTSSzUpIXg6cfhLaTNIQ$0sn1fsX0zRYXv-bb0tV531Brbhb-fPORrXYqe8JzHbnL8NkAwIPRkSaTXfq3etJnmslkkBlPpZeTc7639TWQlBrl3eVW-aQKIjFX4bhfyythIh5ByjBAHw1RaYwoHw10kkpbBBk01K-6pzE9QzT6TvjZLsSsXZ6O3WJdvrB8dtpJA-PI-sOzm7DXkBqfKOSufkN1C1mRvexBlcN3ScSH2TKo5ZwKw3Fo_93GsYFD0hzYmHpC6yCyHXeY1PPlHYqd_KsqXVo_xBtXMCadoKnldYnMljXdhAQJLRdlkwTgeD8FX18SQSJ18O6Ag0w3IM9QXkcgZVgIo1-_ZUncc5AtNXyQCvfT4tNyaIRFsXlFqj5tCc5bekMz8OzYeRTPfmfCLKXmjvg4ICMw0aIRboX1tyZpCHdHU269u0-wX90pMDNRqBZsLag6glNDSzEG8RQaB4vGrjvxYy0ixeUnogwni2qqnnGX5Gfhkst7FPYubAsi5HweDT_aJIrmE6kMiBrpMAOcIGZ6slYK854FOH3ODO9-raz7n2P__NUTpziTF4t4Jru_erJevVoGyHH81qq_msIMvK7IRx2z1QoExRL08A$AES$SHA256
        String content = "{\"response\":\"ZIcrArlonH0mxIWCRejL2VQS2qeK5EFz2gALzdMbusIU8eqwnWNgJRWiTwJElSQEhnT42KkU3jXWZr2dd0A8-bZSjT-hvNCUI0aoJZkadRtJrWoe_ygGhOLegj7cTbk8y7GOzfFQteIFbB9ALae1CqWVHgfgyozbTLgsse4MfuYjio9r3DOkCJJSkW6mEHB0G4rTXSWFni0h_Uhtu5jsuCTU4vWDPKrBIZI17rr1AIqmyOd8C8oLCAplC1JT4KnLq5QCir4cnvZJrGYB5-bI00gPOdGX2_v4Az3VqMkh8PqqPSDriJ-PqDo9T2dnjR5njYkTSSzUpIXg6cfhLaTNIQ$0sn1fsX0zRYXv-bb0tV531Brbhb-fPORrXYqe8JzHbnL8NkAwIPRkSaTXfq3etJnmslkkBlPpZeTc7639TWQlBrl3eVW-aQKIjFX4bhfyythIh5ByjBAHw1RaYwoHw10kkpbBBk01K-6pzE9QzT6TvjZLsSsXZ6O3WJdvrB8dtpJA-PI-sOzm7DXkBqfKOSufkN1C1mRvexBlcN3ScSH2TKo5ZwKw3Fo_93GsYFD0hzYmHpC6yCyHXeY1PPlHYqd_KsqXVo_xBtXMCadoKnldYnMljXdhAQJLRdlkwTgeD8FX18SQSJ18O6Ag0w3IM9QXkcgZVgIo1-_ZUncc5AtNXyQCvfT4tNyaIRFsXlFqj5tCc5bekMz8OzYeRTPfmfCLKXmjvg4ICMw0aIRboX1tyZpCHdHU269u0-wX90pMDNRqBZsLag6glNDSzEG8RQaB4vGrjvxYy0ixeUnogwni2qqnnGX5Gfhkst7FPYubAsi5HweDT_aJIrmE6kMiBrpMAOcIGZ6slYK854FOH3ODO9-raz7n2P__NUTpziTF4t4Jru_erJevVoGyHH81qq_msIMvK7IRx2z1QoExRL08A$AES$SHA256\",\"customerIdentification\":\"app_Fe51qCyZWcEnDMtK\"}";

        webClient.post().uri("/yop-event/opr/payment").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("Success");
        });
    }

    @Test
    public void notifyUnHandler() throws Exception {
        String content = "{\"response\":\"ZIcrArlonH0mxIWCRejL2VQS2qeK5EFz2gALzdMbusIU8eqwnWNgJRWiTwJElSQEhnT42KkU3jXWZr2dd0A8-bZSjT-hvNCUI0aoJZkadRtJrWoe_ygGhOLegj7cTbk8y7GOzfFQteIFbB9ALae1CqWVHgfgyozbTLgsse4MfuYjio9r3DOkCJJSkW6mEHB0G4rTXSWFni0h_Uhtu5jsuCTU4vWDPKrBIZI17rr1AIqmyOd8C8oLCAplC1JT4KnLq5QCir4cnvZJrGYB5-bI00gPOdGX2_v4Az3VqMkh8PqqPSDriJ-PqDo9T2dnjR5njYkTSSzUpIXg6cfhLaTNIQ$0sn1fsX0zRYXv-bb0tV531Brbhb-fPORrXYqe8JzHbnL8NkAwIPRkSaTXfq3etJnmslkkBlPpZeTc7639TWQlBrl3eVW-aQKIjFX4bhfyythIh5ByjBAHw1RaYwoHw10kkpbBBk01K-6pzE9QzT6TvjZLsSsXZ6O3WJdvrB8dtpJA-PI-sOzm7DXkBqfKOSufkN1C1mRvexBlcN3ScSH2TKo5ZwKw3Fo_93GsYFD0hzYmHpC6yCyHXeY1PPlHYqd_KsqXVo_xBtXMCadoKnldYnMljXdhAQJLRdlkwTgeD8FX18SQSJ18O6Ag0w3IM9QXkcgZVgIo1-_ZUncc5AtNXyQCvfT4tNyaIRFsXlFqj5tCc5bekMz8OzYeRTPfmfCLKXmjvg4ICMw0aIRboX1tyZpCHdHU269u0-wX90pMDNRqBZsLag6glNDSzEG8RQaB4vGrjvxYy0ixeUnogwni2qqnnGX5Gfhkst7FPYubAsi5HweDT_aJIrmE6kMiBrpMAOcIGZ6slYK854FOH3ODO9-raz7n2P__NUTpziTF4t4Jru_erJevVoGyHH81qq_msIMvK7IRx2z1QoExRL08A$AES$SHA256\",\"customerIdentification\":\"app_Fe51qCyZWcEnDMtK\"}";

        webClient.post().uri("/yop-event/payment").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().is2xxSuccessful()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).consumeWith(result -> {
            String response = result.getResponseBody();
            assertThat(response).isEqualTo("Fail, cause:商户未处理");
        });
    }

}
