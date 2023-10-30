package aleks.crypto.api.service;


import aleks.crypto.api.domain.CryptoCurrencyResponse;

public interface CryptoCurrencyConverterImpl {
    CryptoCurrencyResponse callAPI(String symbols);
}
