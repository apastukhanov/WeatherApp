package com.pae.ioUtils;

import com.pae.weather.Weather;

public interface HistoryWriter {
    public void save(Weather weather);
}
