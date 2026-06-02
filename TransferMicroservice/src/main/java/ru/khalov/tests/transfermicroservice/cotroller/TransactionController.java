package ru.khalov.tests.transfermicroservice.cotroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.khalov.tests.transfermicroservice.model.TransferRestModel;
import ru.khalov.tests.transfermicroservice.service.TransactionService;

@Slf4j
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping()
    public boolean  transfer(@RequestBody TransferRestModel transferRestModel){
        return transactionService.transfer(transferRestModel);
    }
}
