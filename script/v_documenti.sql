SELECT
d.id as id ,
d.[data]  as data_documento ,
d.tdoc1  as tdoc1  ,
d.tdoc2  as tdoc2 ,
d.c_remi_ass  as remi ,
'linea' as linea ,  -- per ogni remi ci possono essere piu linee --> potrebbe essere una lista
ar.c_unita as codice_centro,
au.c_unita_app as codice_distretto ,
ar.codice_area as codice_area_tecnica ,
atc.codice_polo as codice_polo ,
acc.x_rag_soc as ragione_sociale ,
ar.s_remi as ubicazione,  --- TODO chiedi se va bene ar.ubicazione perche la maggior parte è null
au.g_pr as provincia ,
au.n_comune as comune ,
pc.descrizione as regione ,
srac.c_aop as codice_aop ,
ar.c_ateco as codice_ateco ,
ar.d_ini_eserc as data_entrata_esercizio,
'CodiceREMITerzo' as remi_terzo , -- ? potrebbe esser c_eserc su anag_remi?
'RetiDiTrasporto' as reti_trasporto -- ? cosa rappresenta

FROM  documenti d , anag_remi ar
	join anag_unita au  on au.c_unita = ar.c_unita
	join area_tecnica_consumer atc on atc.codice_area = ar.codice_area
	join anag_clienti_consumer acc on acc.c_cliente = ar.c_cliente
	join stor_remi_aop_consumer srac  on srac.c_remi_ass = ar.c_remi_ass
		and srac.d_ini_aop_long = (
		SELECT max(srac2.d_ini_aop_long) from stor_remi_aop_consumer srac2 where srac2.c_remi_ass = ar.c_remi_ass
		)
	join polo_consumer pc  on pc.codice_polo = atc.codice_polo
where d.c_remi_ass  = ar.c_remi_ass






