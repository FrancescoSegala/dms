package it.eng.snam.summer.dmsmisuraservice.controller;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.mapOf;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.date;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.listOf;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.number;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.required;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.singleOf;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.stringOf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.Attachment;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.create.AttachmentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.AttachmentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.AttachmentUpdate;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidator;
import it.eng.snam.summer.dmsmisuraservice.util.validation.RemiInfoValidator;

@RestController
@CrossOrigin
public class DocumentController {

    @Autowired
    DDS dds;

    @Autowired
    Summer summer;

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> list(@Valid DocumentSearch params) {
        // TODO a che serve ?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // da authentication èpossibile recujperare il Profilo (tramite la proprietà
        // Principale)
        // e la lista delle funzionalità (proprietà authorioties) cui l'utente loggato è
        // abilitato
        HttpHeaders headers = new HttpHeaders();
        if (params.isCount()) {
            headers.add("count", "" + summer.countDocuments(params));
        }
        return new ResponseEntity<>(summer.listDocuments(params), headers, HttpStatus.OK);

        // return summer.listDocuments(params);
    }

    @GetMapping("/documents/{document_id}")
    public Document get(@PathVariable String document_id) {
        return dds.getDocument(document_id);
    }

    @GetMapping("/documents/{document_id}/content")
    public void getContent(HttpServletResponse response, @PathVariable String document_id) {
        byte[] content = dds.getContent(document_id);
        try {
            response.getOutputStream().write(content);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    // TODO
    @PutMapping("/documents/{document_id}/content")
    public void putContent(@PathVariable String document_id, MultipartFile file, HttpServletResponse response) {

    }

    // TODO
    @GetMapping("/documents/{document_id}/attachments")
    public List<Attachment> listAttachment(@Valid AttachmentSearch params) {
        return null;
    }

    // TODO
    @PostMapping("/documents/{document_id}/attachments")
    public Attachment postAttachment(@RequestBody @Valid AttachmentCreate[] params, MultipartFile[] file) {
        return null;
    }

    // TODO
    @GetMapping("/documents/{document_id}/attachments/{attachment_id}")
    public Attachment getAttachment(@PathVariable String document_id, @PathVariable String attachment_id) {
        return null;
    }

    // TODO
    @PutMapping("/documents/{document_id}/attachments/{attachment_id}")
    public Attachment putAttachment(@PathVariable String document_id, @PathVariable String attachment_id,
            @RequestBody @Valid AttachmentUpdate body) {
        return null;
    }

    // TODO
    @DeleteMapping("/documents/{document_id}/attachments/{attachment_id}")
    public void deleteAttachment(@PathVariable String document_id, @PathVariable String attachment_id) {
        // nop
    }

    // TODO
    @GetMapping("/documents/{document_id}/attachments/{attachment_id}/content")
    public void getAttachmentContent(@PathVariable String document_id, @PathVariable String attachment_id,
            HttpServletResponse response) {
        // nop
    }

    // TODO
    @PutMapping("/documents/{document_id}/attachments/{attachment_id}/content")
    public void putAttachmentContent(@PathVariable String document_id, @PathVariable String attachment_id,
            MultipartFile file, HttpServletResponse response) {
        // nop
    }


    private void validatePost(DocumentCreate o) {
        //@formatter:off
        Set<String> errors = o.getSubfolders().stream()
                .map(subfolder -> summer.validation(subfolder)) // summer.validation() -> lista di entity per la validazione della sottocartella
                .flatMap(e -> e.stream()) // fa uno stream soltanto di tutte le liste che c'erano : Stream<Entity>
                .map(e -> toValidator(e))
                .flatMap(e -> e.stream()) // lista di tutti i validatori da applicare a tutti gli info per la sottocartella <subfolder>
                .map(e -> o.getInfo().stream().map(x -> e.apply(x))).flatMap(e -> e).filter(e -> e != null) // apply dei validator su ognina delle info
                .collect(Collectors.toSet());
        //@formatter:on
        if (errors.size() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Entity.stringfy(errors));
        }
    }

    @Autowired
    RemiInfoValidator remiValidator ;
    private List<InfoValidator> toValidator(Entity e) {
        List<InfoValidator> list = new ArrayList<>();
        String nome = e.getAsString("nome");
        String tipo = e.getAsString("tipo");
        //@formatter:off
        Map<String, InfoValidator> type = mapOf(
                "testo", stringOf(nome, (Number) e.get("lunghezza")),
                "numero", number(nome),
                "data", date(nome),
                "remi", remiValidator.withField(nome) );
        list.add("s".equals(e.getAsString("obbligatorio")) ? required(nome) : null);
        list.add("s".equals(e.getAsString("singolo")) ? singleOf(nome, type.get(tipo)) : listOf(nome, type.get(tipo)));
        //@formatter:off
        return list.stream().filter(x -> x != null).collect(Collectors.toList());
    }

    @PostMapping(path = "/documents", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.MULTIPART_MIXED_VALUE })
    public List<Document> post(@RequestPart("document") String document, @RequestPart("file") MultipartFile file) {
        DocumentCreate dc = DocumentCreate.parseJson(document);
        validatePost(dc);
        return dds.createDocument(dc, file);
    }

    @PutMapping("/documents/{document_id}")
    public Document put(@PathVariable String document_id, @RequestBody @Valid DocumentUpdate params) {
        return dds.updateDocument(document_id, params);
    }

    @DeleteMapping("/documents/{document_id}")
    public void delete(@PathVariable String document_id) {
        dds.deleteDocument(document_id);
    }

}
