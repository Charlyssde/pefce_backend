package com.devx.software.ferias.Misc;

import com.devx.software.ferias.DTOs.Meetings.MeetingsAccountCredentialsRequest;
import com.devx.software.ferias.DTOs.Meetings.MeetingsAccountCredentialsResponse;
import com.devx.software.ferias.Entities.Meetings.MeetingAccountEntity;
import com.devx.software.ferias.Entities.Meetings.MeetingZoomEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.json.JSONObject;

import java.util.Base64;

public class Meetings {
    private final String authEndpoint = "https://zoom.us/oauth/token";
    private final String apiEndpoint = "https://api.zoom.us/v2/";
//    private String zoomUserId = "sedecop.zoom@gmail.com";
//    private String zoomUserPwd = "SDCP.20xx";
//    private String zoomAccountId = "wm3yn87tQkeggl5B-1Li_g";
//    private String zoomClientId = "leUaxPQRBiR_W_InZWUMQ";
//    private String zoomClientSecret = "x1gfsbJqXKqjLKlAtsbJ4XTdxMDx4Co0";
//    private String zoomSecretToken_Features = "ByfjTXrOTciackp38akoCQ";
//    private String zoomSecretVerification_Features = "bHnpXfz2QgKs5XvchkAhhQ";


    public MeetingsAccountCredentialsResponse getMeetingAuthTokenResponse(MeetingsAccountCredentialsRequest meetingsAccountCredentialsRequest) throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        String auth = this.getMeetingBasicAuthToken(meetingsAccountCredentialsRequest.getZoomClientId(), meetingsAccountCredentialsRequest.getZoomClientSecret());
        requestHeaders.set(HttpHeaders.AUTHORIZATION, auth);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "account_credentials");
        params.add("account_id", meetingsAccountCredentialsRequest.getZoomAccountId());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(this.authEndpoint, request, MeetingsAccountCredentialsResponse.class);
    }

    public String getMeetingBasicAuthToken(String clientId, String clientSecret) {
        String
                credentials = clientId + ":" + clientSecret,
                basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        return basicAuth;
    }


    public String createMeeting(MeetingZoomEntity meetingZoomEntity, MeetingAccountEntity meetingCuenta) throws Exception {
        MeetingsAccountCredentialsRequest meetingsAccountCredentialsRequest = new MeetingsAccountCredentialsRequest();
        meetingsAccountCredentialsRequest.setZoomAccountId(meetingCuenta.getZoomAccountId());
        meetingsAccountCredentialsRequest.setZoomClientId(meetingCuenta.getZoomClientId());
        meetingsAccountCredentialsRequest.setZoomClientSecret(meetingCuenta.getZoomClientSecret());
        MeetingsAccountCredentialsResponse accountCredentialsResponse = this.getMeetingAuthTokenResponse(meetingsAccountCredentialsRequest);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.AUTHORIZATION, accountCredentialsResponse.getToken_type() + " " + accountCredentialsResponse.getAccess_token());

        String
                uri = this.apiEndpoint + "users/me/meetings",
                jsonResponse = "";


        HttpEntity<MeetingZoomEntity> request = new HttpEntity<MeetingZoomEntity>(meetingZoomEntity, requestHeaders);

        RestTemplate restTemplate = new RestTemplate();

        Object response = restTemplate.postForObject(uri, request, Object.class);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        jsonResponse = ow.writeValueAsString(response);

        return jsonResponse;
    }

    public String updateMeeting(MeetingZoomEntity meetingZoomEntity, MeetingAccountEntity meetingCuenta) throws Exception {
        MeetingsAccountCredentialsRequest meetingsAccountCredentialsRequest = new MeetingsAccountCredentialsRequest();
        meetingsAccountCredentialsRequest.setZoomAccountId(meetingCuenta.getZoomAccountId());
        meetingsAccountCredentialsRequest.setZoomClientId(meetingCuenta.getZoomClientId());
        meetingsAccountCredentialsRequest.setZoomClientSecret(meetingCuenta.getZoomClientSecret());
        MeetingsAccountCredentialsResponse accountCredentialsResponse = this.getMeetingAuthTokenResponse(meetingsAccountCredentialsRequest);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.AUTHORIZATION, accountCredentialsResponse.getToken_type() + " " + accountCredentialsResponse.getAccess_token());

        String strJson = meetingZoomEntity.getMeetingId().getApiResponse();

        JSONObject object = new JSONObject(strJson);

        String
                uri = this.apiEndpoint + "users/me/meetings/" + object.get("id"),
                jsonResponse = "";


        HttpEntity<MeetingZoomEntity> request = new HttpEntity<MeetingZoomEntity>(meetingZoomEntity, requestHeaders);

        RestTemplate restTemplate = new RestTemplate();

        Object response = restTemplate.postForObject(uri, request, Object.class);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        jsonResponse = ow.writeValueAsString(response);

        return jsonResponse;
    }

//    public void deleteMeeting(Long zoomId, MeetingCuentaEntity meetingCuenta) throws Exception {
//        MeetingsAccountCredentialsRequest meetingsAccountCredentialsRequest = new MeetingsAccountCredentialsRequest();
//        meetingsAccountCredentialsRequest.setZoomAccountId(meetingCuenta.getZoomAccountId());
//        meetingsAccountCredentialsRequest.setZoomClientId(meetingCuenta.getZoomClientId());
//        meetingsAccountCredentialsRequest.setZoomClientSecret(meetingCuenta.getZoomClientSecret());
//        MeetingsAccountCredentialsResponse accountCredentialsResponse = this.getMeetingAuthTokenResponse(meetingsAccountCredentialsRequest);
//
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set(HttpHeaders.AUTHORIZATION,accountCredentialsResponse.getToken_type()+" "+accountCredentialsResponse.getAccess_token());
//
//        String  uri = this.apiEndpoint+"meetings/"+Long.toString(zoomId);
//        HttpEntity<String> request = new HttpEntity<String>(null,requestHeaders);
//        RestTemplate restTemplate = new RestTemplate();
//        //  Get meeting
//        ResponseEntity<String> data = restTemplate.exchange(uri,HttpMethod.GET,request,String.class);
//
////        restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
//    }

}
