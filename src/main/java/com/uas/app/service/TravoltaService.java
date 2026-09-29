package com.uas.app.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class TravoltaService {

    public Map<String, Object> hitungGajiDanTabungan(double jamKerja, double pengeluaran) {
        double rateNormal = 15000;
        double jamNormal = Math.min(jamKerja, 40);
        double jamLembur = Math.max(0, jamKerja - 40);

        double gajiNormal = jamNormal * rateNormal;
        double gajiLembur = jamLembur * (rateNormal * 1.5);
        double totalPemasukan = gajiNormal + gajiLembur;

        double selisih = totalPemasukan - pengeluaran;
        String statusFinansial;
        double sisaTabungan = 0;

        if (totalPemasukan > pengeluaran) {
            statusFinansial = "bisa menabung";
            sisaTabungan = selisih;
        } else if (totalPemasukan == pengeluaran) {
            statusFinansial = "tidak bisa menabung";
        } else {
            statusFinansial = "cari tambahan";
        }

        Map<String, Object> hasil = new HashMap<>();
        hasil.put("jamKerja", jamKerja);
        hasil.put("jamNormal", jamNormal);
        hasil.put("jamLembur", jamLembur);
        hasil.put("gajiNormal", gajiNormal);
        hasil.put("gajiLembur", gajiLembur);
        hasil.put("totalPemasukan", totalPemasukan);
        hasil.put("pengeluaran", pengeluaran);
        hasil.put("statusFinansial", statusFinansial);
        hasil.put("sisaTabungan", sisaTabungan);

        return hasil;
    }
}