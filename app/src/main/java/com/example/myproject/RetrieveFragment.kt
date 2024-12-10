package com.example.myproject

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.element.Image
import com.itextpdf.io.image.ImageDataFactory
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class RetrieveFragment : Fragment() {

    private lateinit var tableLayout: TableLayout
    private lateinit var retrieveButton: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var btn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_retrieve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()

        tableLayout = view.findViewById(R.id.tableLayout)
        retrieveButton = view.findViewById(R.id.retrieveButton)
        btn = view.findViewById(R.id.hyphen)

        retrieveButton.setOnClickListener {
            fetchAndDisplayData()
        }

        btn.setOnClickListener {
            val fragment = CFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container_new, fragment)?.commit()
        }
    }

    private fun fetchAndDisplayData() {
        tableLayout.removeAllViews()
        addTableHeader()

        val quizResultsRef = database.reference.child("quizResults")
        quizResultsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val quizResult = data.getValue(QuizResult::class.java)
                    if (quizResult != null) {
                        addTableRow(quizResult)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })
    }

    private fun addTableHeader() {
        val headerRow = TableRow(requireContext())

        val nameHeader = TextView(requireContext())
        nameHeader.text = "Name"
        nameHeader.textSize = 18f

        val rollNoHeader = TextView(requireContext())
        rollNoHeader.text = "Roll No"
        rollNoHeader.textSize = 18f

        val scoreHeader = TextView(requireContext())
        scoreHeader.text = "Score"
        scoreHeader.textSize = 18f

        val downloadHeader = TextView(requireContext())
        downloadHeader.text = "Download"
        downloadHeader.textSize = 18f

        headerRow.addView(nameHeader)
        headerRow.addView(rollNoHeader)
        headerRow.addView(scoreHeader)
        headerRow.addView(downloadHeader)

        tableLayout.addView(headerRow)
    }

    private fun addTableRow(quizResult: QuizResult) {
        val row = TableRow(requireContext())

        val nameTextView = TextView(requireContext())
        nameTextView.text = quizResult.name

        val rollNoTextView = TextView(requireContext())
        rollNoTextView.text = quizResult.rollNo

        val scoreTextView = TextView(requireContext())
        scoreTextView.text = quizResult.score.toString()

        val downloadButton = Button(requireContext())
        downloadButton.text = "Download"
        if (quizResult.score > 5) {
            downloadButton.visibility = View.VISIBLE
            downloadButton.setOnClickListener {
                downloadCertificate(quizResult)
            }
        } else {
            downloadButton.visibility = View.GONE
        }

        row.addView(nameTextView)
        row.addView(rollNoTextView)
        row.addView(scoreTextView)
        row.addView(downloadButton)

        tableLayout.addView(row)
    }

    private fun downloadCertificate(quizResult: QuizResult) {
        context?.let { safeContext ->
            val contentResolver = safeContext.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "${quizResult.rollNo}_certificate.pdf")
                put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }

            val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
            uri?.let {
                contentResolver.openOutputStream(it)?.use { outputStream ->
                    val writer = PdfWriter(outputStream)
                    val pdfDoc = PdfDocument(writer)
                    val document = Document(pdfDoc)

                    val backgroundDrawable = safeContext.getDrawable(R.drawable.certificate_image) as? BitmapDrawable
                        ?: return
                    val backgroundBitmap = backgroundDrawable.bitmap

                    val backgroundStream = ByteArrayOutputStream()
                    backgroundBitmap.compress(Bitmap.CompressFormat.PNG, 100, backgroundStream)
                    val backgroundImageData = ImageDataFactory.create(backgroundStream.toByteArray())

                    val backgroundImage = Image(backgroundImageData)
                    backgroundImage.scaleToFit(pdfDoc.defaultPageSize.width, pdfDoc.defaultPageSize.height)
                    backgroundImage.setFixedPosition(0f, 0f)

                    document.add(backgroundImage)
                    val logoDrawable = safeContext.getDrawable(R.drawable.back6) as? BitmapDrawable
                        ?: return
                    val logoBitmap = logoDrawable.bitmap
                    val logoStream = ByteArrayOutputStream()
                    logoBitmap.compress(Bitmap.CompressFormat.PNG, 100, logoStream)
                    val logoImageData = ImageDataFactory.create(logoStream.toByteArray())

                    val logoImage = Image(logoImageData)
                    logoImage.scaleToFit(150f, 150f)
                    logoImage.setFixedPosition(0f, pdfDoc.defaultPageSize.height - 200f)

                    document.add(logoImage)
                    document.add(Paragraph("Certificate of Achievement")
                        .setBold()
                        .setFontSize(30f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(120f))

                    document.add(Paragraph("This certificate is awarded to")
                        .setFontSize(27f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(40f))
                        .setBold()

                    document.add(Paragraph("${quizResult.name}")
                        .setBold()
                        .setFontSize(24f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(10f))
                        .setMargins(10f,10f,10f,10f)

                    document.add(Paragraph("In recognition of their remarkable achievements and exceptional contributions to")
                        .setFontSize(18f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(30f))

                    document.add(Paragraph("C Language")
                        .setBold()
                        .setFontSize(20f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(10f))

                    document.add(Paragraph("On")
                        .setFontSize(18f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(30f))

                    document.add(Paragraph(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(java.util.Date()))
                        .setBold()
                        .setFontSize(20f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(10f))

                    document.add(Paragraph("Your hard work, determination, and passion have set a high standard of excellence, and we are proud to recognize your accomplishments.")
                        .setFontSize(16f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(30f))

                    document.close()
                    Toast.makeText(safeContext, "Certificate saved to Downloads", Toast.LENGTH_LONG).show()
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/pdf")
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    }
                    safeContext.startActivity(intent)
                }
            } ?: run {
                Toast.makeText(safeContext, "Failed to create certificate file", Toast.LENGTH_LONG).show()
            }
        }
    }
}
