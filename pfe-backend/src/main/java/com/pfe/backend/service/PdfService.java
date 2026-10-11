package com.pfe.backend.service;

import java.io.ByteArrayOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.pfe.backend.model.*;
import com.pfe.backend.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final StageRepository stageRepository;

    public byte[] generateStageReport(Long stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new RuntimeException("Stage non trouvé"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // ── Titre ──────────────────────────────────────────
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Rapport de Stage PFE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            document.add(new LineSeparator());
            document.add(Chunk.NEWLINE);

            // ── Informations du stage ──────────────────────────
            addSection(document, "Informations du Stage");
            addField(document, "Sujet", stage.getSujet());
            addField(document, "Date début", stage.getDateDebut().toString());
            addField(document, "Date fin", stage.getDateFin().toString());
            addField(document, "Description", stage.getDescription());
            addField(document, "Objectifs", stage.getObjectifs());
            addField(document, "Solution", stage.getSolution());
            addField(document, "Démarche", stage.getDemarche());
            addField(document, "Outils", stage.getOutils());

            // ── Informations de l'étudiant ─────────────────────
            if (stage.getEtudiant() != null) {
                Etudiant e = stage.getEtudiant();
                addSection(document, "Étudiant");
                addField(document, "Nom complet", e.getNom() + " " + e.getPrenom());
                addField(document, "Email", e.getEmail());
                addField(document, "CNE", e.getCne());
                addField(document, "Téléphone", e.getTel());
                if (e.getFiliere() != null)
                    addField(document, "Filière", e.getFiliere().getIntitule());
            }

            // ── Informations de l'entreprise ───────────────────
            if (stage.getEntreprise() != null) {
                Entreprise ent = stage.getEntreprise();
                addSection(document, "Entreprise d'accueil");
                addField(document, "Nom", ent.getNom());
                addField(document, "Adresse", ent.getAdresse());
                addField(document, "Ville", ent.getVille() + ", " + ent.getPays());
                addField(document, "Email", ent.getEmail());
                addField(document, "Téléphone", ent.getTel());
            }

            // ── Encadrant professionnel ────────────────────────
            if (stage.getEncadrantPro() != null) {
                EncadrantPro ep = stage.getEncadrantPro();
                addSection(document, "Encadrant Professionnel");
                addField(document, "Email", ep.getEmail());
                addField(document, "Téléphone", ep.getTel());
            }

            // ── Encadrant académique ───────────────────────────
            if (stage.getEncadrantAcademique() != null) {
                EncadrantAcademique ea = stage.getEncadrantAcademique();
                addSection(document, "Encadrant Académique");
                addField(document, "Nom", ea.getNom());
                addField(document, "Département", ea.getDepartement());
                addField(document, "Établissement", ea.getEtablissement());
            }

            // ── Tâches ─────────────────────────────────────────
            if (stage.getTaches() != null && !stage.getTaches().isEmpty()) {
                addSection(document, "Tâches");
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10);

                // En-têtes
                Font headerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
                table.addCell(new Phrase("Description", headerFont));
                table.addCell(new Phrase("Date début", headerFont));
                table.addCell(new Phrase("Date fin", headerFont));

                for (Tache t : stage.getTaches()) {
                    table.addCell(t.getDescription());
                    table.addCell(t.getDateDebut() != null ? t.getDateDebut().toString() : "-");
                    table.addCell(t.getDateFin() != null ? t.getDateFin().toString() : "-");
                }
                document.add(table);
            }

            // ── Étapes ─────────────────────────────────────────
            if (stage.getEtapes() != null && !stage.getEtapes().isEmpty()) {
                addSection(document, "Étapes du projet");
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10);

                Font headerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
                table.addCell(new Phrase("Étape", headerFont));
                table.addCell(new Phrase("Date prévue", headerFont));
                table.addCell(new Phrase("Date réalisation", headerFont));

                for (Etape et : stage.getEtapes()) {
                    table.addCell(et.getNom());
                    table.addCell(et.getDatePrevue() != null ? et.getDatePrevue().toString() : "-");
                    table.addCell(et.getDateRealisation() != null ? et.getDateRealisation().toString() : "-");
                }
                document.add(table);
            }

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Erreur génération PDF : " + e.getMessage());
        }

        return outputStream.toByteArray();
    }

    // ── Helpers ────────────────────────────────────────────────
    private void addSection(Document doc, String title) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        Paragraph section = new Paragraph(title, sectionFont);
        section.setSpacingBefore(15);
        section.setSpacingAfter(8);
        doc.add(section);
        doc.add(new LineSeparator());
    }

    private void addField(Document doc, String label, String value) throws DocumentException {
        Font labelFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        Font valueFont = new Font(Font.FontFamily.HELVETICA, 11);
        Paragraph p = new Paragraph();
        p.add(new Chunk(label + " : ", labelFont));
        p.add(new Chunk(value != null ? value : "-", valueFont));
        p.setSpacingAfter(5);
        doc.add(p);
    }
}
