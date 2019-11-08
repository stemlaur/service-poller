package com.livi.servicepoller.domain;

public interface ServiceHealth {
    Status check(String url);
}
