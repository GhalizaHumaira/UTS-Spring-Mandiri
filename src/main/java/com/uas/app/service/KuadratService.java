package com.uas.app.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class KuadratService {

    public Map<String, Object> hitungAkar(double a, double b, double c) {
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("a", a);
        hasil.put("b", b);
        hasil.put("c", c);

        if (a == 0) {
            hasil.put("status", "Bukan persamaan kuadrat (nilai a tidak boleh 0)");
            return hasil;
        }

        double d = (b * b) - (4 * a * c);
        hasil.put("diskriminan", d);

        if (d > 0) {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            hasil.put("tipe", "Dua akar real berbeda");
            hasil.put("x1", x1);
            hasil.put("x2", x2);
        } else if (d == 0) {
            double x1 = -b / (2 * a);
            hasil.put("tipe", "Satu akar kembar");
            hasil.put("x1", x1);
            hasil.put("x2", x1);
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-d) / (2 * a);
            hasil.put("tipe", "Akar imajiner / kompleks");
            hasil.put("x1", String.format("%.2f + %.2fi", realPart, imaginaryPart));
            hasil.put("x2", String.format("%.2f - %.2fi", realPart, imaginaryPart));
        }

        return hasil;
    }
}