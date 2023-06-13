package asn2.student_tracker.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asn2.student_tracker.Models.Student;
import asn2.student_tracker.Models.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentsController {
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/home")
    public String welcomePage(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("studentCount", students.size());
        return "students/home";
    }
    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        List<Student> students  = studentRepo.findAll();
        model.addAttribute("st", students);
        return "students/list-all";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response, Model model) {
        String newName = newStudent.get("name");
        Double newWeight = Double.parseDouble(newStudent.get("weight"));
        Double newHeight = Double.parseDouble(newStudent.get("height"));
        String newHairColor = newStudent.get("hair-color");
        Double newGPA = Double.parseDouble(newStudent.get("gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newGPA, newHairColor));
        response.setStatus(201);
        List<Student> students  = studentRepo.findAll();
        model.addAttribute("st", students);
        return "redirect:/students/view";
    }
    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam("id") int sid, HttpServletResponse response) {
        studentRepo.deleteById(sid);
        return "redirect:/students/view";
    }

    @GetMapping("/student/{id}")
    public String getStudentId(@PathVariable("id") int id, Model model) {
        Student student = studentRepo.findBySid(id);
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/student/edit")
    public String editStudent(@RequestParam Map<String, String> editedStudent) {
        // Update the student properties with the new values
        int id = Integer.parseInt(editedStudent.get("sid"));
        Student student = studentRepo.findBySid(id);

        String newName = editedStudent.get("name");
        double newWeight = Double.parseDouble(editedStudent.get("weight"));
        double newHeight = Double.parseDouble(editedStudent.get("height"));
        String newHairColor = editedStudent.get("hairColor");
        double newGPA = Double.parseDouble(editedStudent.get("gpa"));
        student.setName(newName);
        student.setWeight(newWeight);
        student.setHeight(newHeight);
        student.setHairColor(newHairColor);
        student.setGpa(newGPA);
        
        // Save the modified student object
        studentRepo.save(student);
        
        return "redirect:/students/view";
    }


}
