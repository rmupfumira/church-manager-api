package za.org.rfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

import za.org.rfm.entity.Assembly;
import za.org.rfm.entity.Transaction;
import za.org.rfm.entity.TransactionResponseObj;
import za.org.rfm.service.TransactionService;
import za.org.rfm.utils.Constants;
import za.org.rfm.utils.GeneralUtils;

@Controller
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("assembly/{id}/{startDate}/{endDate}")
    public ResponseEntity<TransactionResponseObj> getTransactionByAssembly(@PathVariable("id") Integer id,@PathVariable("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String startDate,
                                                                           @PathVariable("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String endDate) {
        List<Transaction> list = transactionService.getTransactionsByAssembly(id,GeneralUtils.getDateFromString(Constants.DATE_FORMAT_TXN,startDate),GeneralUtils.getDateFromString(Constants.DATE_FORMAT_TXN,endDate));
        TransactionResponseObj transactionResponseObj = new TransactionResponseObj();
        transactionResponseObj.setTransactionList(list);
        return new ResponseEntity<TransactionResponseObj>(transactionResponseObj, HttpStatus.OK);
    }

    @GetMapping("assembly/{id}/{startDate}/{endDate}/{type}")
    public ResponseEntity<TransactionResponseObj> getTransactionByAssembly(@PathVariable("id") Integer id,@PathVariable("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String startDate,
                                                           @PathVariable("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) String endDate,@PathVariable("type") String type) {
        List<Transaction> list = transactionService.getTransactionsByAssembly(id,GeneralUtils.getDateFromString(Constants.DATE_FORMAT_TXN,startDate),GeneralUtils.getDateFromString(Constants.DATE_FORMAT_TXN,endDate),type);
        TransactionResponseObj transactionResponseObj = new TransactionResponseObj();
        transactionResponseObj.setTransactionList(list);
        return new ResponseEntity<TransactionResponseObj>(transactionResponseObj, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction,@PathVariable("id") Integer id, UriComponentsBuilder builder) {
        transactionService.addTransaction(transaction,id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("{id}").buildAndExpand(transaction.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}