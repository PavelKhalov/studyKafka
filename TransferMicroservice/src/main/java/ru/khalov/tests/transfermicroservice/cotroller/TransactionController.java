package ru.khalov.tests.transfermicroservice.cotroller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.khalov.tests.transfermicroservice.model.TransferRestModel;
import ru.khalov.tests.transfermicroservice.service.TransactionService;


@Tag(name = "swagger Doc transaction controller")
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
