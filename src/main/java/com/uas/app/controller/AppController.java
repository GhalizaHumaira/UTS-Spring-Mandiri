package com.uas.app.controller;

import com.uas.app.service.KuadratService;
import com.uas.app.service.TravoltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private TravoltaService travoltaService;

    @Autowired
    private KuadratService kuadratService;

    // ==========================================
    // HALAMAN UTAMA / MENU GABUNGAN (http://localhost:8080)
    // ==========================================
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // ==========================================
    // SOAL 2: JOHN TRAVOLTA
    // ==========================================

    // Tampilan Form Pertama Kali (GET)
    @GetMapping("/travolta")
    public String showTravoltaForm(Model model) {
        model.addAttribute("defaultPengeluaran", 600000);
        return "travolta";
    }

    // Memproses Input dari Form (POST)
    @PostMapping("/travolta")
    public String processTravoltaForm(@RequestParam("jamKerja") double jamKerja,
                                      @RequestParam(value = "pengeluaran", defaultValue = "600000") double pengeluaran,
                                      Model model) {
        Map<String, Object> hasil = travoltaService.hitungGajiDanTabungan(jamKerja, pengeluaran);
        model.addAttribute("hasil", hasil);
        model.addAttribute("defaultPengeluaran", pengeluaran);
        return "travolta";
    }

    // Versi REST API JSON (Tambahan Nilai ++)
    @GetMapping("/api/travolta")
    @ResponseBody
    public Map<String, Object> apiTravolta(@RequestParam("jamKerja") double jamKerja,
                                           @RequestParam(value = "pengeluaran", defaultValue = "600000") double pengeluaran) {
        return travoltaService.hitungGajiDanTabungan(jamKerja, pengeluaran);
    }

    // ==========================================
    // SOAL 3: PERSAMAAN KUADRAT
    // ==========================================

    // Tampilan Form Pertama Kali (GET)
    @GetMapping("/kuadrat")
    public String showKuadratForm() {
        return "kuadrat";
    }

    // Memproses Input dari Form (POST)
    @PostMapping("/kuadrat")
    public String processKuadratForm(@RequestParam("a") double a,
                                     @RequestParam("b") double b,
                                     @RequestParam("c") double c,
                                     Model model) {
        Map<String, Object> hasil = kuadratService.hitungAkar(a, b, c);
        model.addAttribute("hasil", hasil);
        return "kuadrat";
    }

    // Versi REST API JSON (Tambahan Nilai ++)
    @GetMapping("/api/kuadrat")
    @ResponseBody
    public Map<String, Object> apiKuadrat(@RequestParam("a") double a,
                                          @RequestParam("b") double b,
                                          @RequestParam("c") double c) {
        return kuadratService.hitungAkar(a, b, c);
    }
}