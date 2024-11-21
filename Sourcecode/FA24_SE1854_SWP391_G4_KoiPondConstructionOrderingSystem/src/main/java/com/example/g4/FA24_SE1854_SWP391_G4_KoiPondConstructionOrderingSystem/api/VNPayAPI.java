package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.config.VNPayConfig;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.PaymentVNPayResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServicePaymentRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.TransactionResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServicePaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author VNPayAPI
 */
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
@RequestMapping("api/vnpay")
public class VNPayAPI {
    @Autowired
    private ServicePaymentService servicePaymentService;


    @GetMapping("/create_payment")
public ResponseEntity<?> createVnPay(@RequestParam(value = "amount") long amount, @RequestParam(value = "paymentId") Integer paymentId, HttpServletRequest req) throws UnsupportedEncodingException {
        //String vnp_RequestId = Config.getRandomNumber(8);
        //String orderType = "other";
       // long amount = Integer.parseInt(req.getParameter("amount"))*100;
       // String bankCode = req.getParameter("bankCode");
        //long amount =1000000;
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Số tiền không hợp lệ!");
        }
        amount = amount * 100;
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
       // String vnp_IpAddr = VNPayConfig.getIpAddress(req);

        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String vnp_IpAddr = VNPayConfig.getIpAddress(req);
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode","NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl",VNPayConfig.vnp_ReturnUrl + "?paymentId=" + paymentId);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_OrderType", VNPayConfig.orderType);

//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
      //  vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
      //  vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        PaymentVNPayResponse paymentVNPayResponse = new PaymentVNPayResponse();
        paymentVNPayResponse.setStatus("OK");
        paymentVNPayResponse.setMessage("Successfully");
        paymentVNPayResponse.setURL(paymentUrl);




//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
        return ResponseEntity.status(HttpStatus.OK).body(paymentVNPayResponse);
    }

    @GetMapping("/payment_info")
    public ResponseEntity<?> transaction(
            @RequestParam (value ="vnp_Amount") String amount,
            @RequestParam (value ="vnp_BankCode") String bankCode,
            @RequestParam (value ="vnp_OrderInfo") String order,
            @RequestParam (value ="vnp_ResponseCode") String responseCode,
            @RequestParam(value = "paymentId") Integer paymentId
    )
    {


        TransactionResponse transactionResponse = new TransactionResponse();
        if(responseCode.equals("00"))
        {
            ServicePaymentRequest servicePaymentRequest = new ServicePaymentRequest();
            servicePaymentRequest.setPaymentMethod("Online");
            servicePaymentRequest.setStatus("Paid");
            servicePaymentRequest.setTransactionID(order);
           servicePaymentService.updateServicePayment(paymentId,servicePaymentRequest);

            transactionResponse.setStatus("OK");
            transactionResponse.setMessage("Successfully");
            transactionResponse.setData("");


        }
        else {
        transactionResponse.setStatus("No");
        transactionResponse.setMessage("Failed");
        transactionResponse.setData("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }


}
