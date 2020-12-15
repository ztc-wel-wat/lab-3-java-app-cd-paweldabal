/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.wat.wel.javaapp;

/**
 *
 * @author pdaba
 */
public enum CmdType {
    RST, // Zdalny reset mikrokontrolera
    LED, // Sterowanie I/O do których podłączone są diody LED
    RGB, // Sterowanie I/O do których podłączona dioda LED RGB
    LCD, // Sterowanie wyświetlaczem LCD
    SEG, // Sterowanie wyświetlaczem 7 segmentowym
    JOY, // Odczyt stanu joysticka
    ACC, // Odczyt wskazań akcelerometru
    MAG, // Odczyt wskazań magnetometru
    TEM, // Odczyt czujnika temperatury
    ADC, // Odczyt naięcia na potencjometrze
    MIC, // Odczyt zarejestrowanego sygału z mikrofonu
    SPK, // Zapis syganłu na wyjściu Audio Out
    MOT, // Sterowanie I/O dla silnika prądu stałego
    MEM	 // Odczyt/zapis do pamięci QSPI
}
