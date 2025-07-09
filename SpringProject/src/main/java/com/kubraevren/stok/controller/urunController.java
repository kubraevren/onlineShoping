package com.kubraevren.stok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kubraevren.eticaret.model.siparis;

@Controller
public class urunController {
	@Autowired
	private com.kubraevren.eticaret.service.siparisService siparisService;

	@GetMapping("/siparisler")
	public List<siparis> tumSiparisleriGetir() {
		return siparisService.tumSiparisleriGetir();
	}

	;


}
