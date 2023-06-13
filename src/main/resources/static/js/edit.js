
document.getElementById("edit-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission
    let sid = document.getElementById("sid").value;
    // Get the values from the form inputs
    let name = document.getElementById("name").value;
    let weight = document.getElementById("weight").value;
    let height = document.getElementById("height").value;
    let hairColor = document.getElementById("hair-color").value;
    let gpa = document.getElementById("gpa").value;
    
    // Call the editStudent function with the obtained values
    editStudent(sid, name, weight, height, hairColor, gpa);
  });
  
  function editStudent(sid, name, weight, height, hairColor, gpa) {
    if (confirm("Are you sure you want to make this change?")) {
      $.ajax({
        type: 'POST',
        url: '/student/edit',
        data: { 
          sid: sid,
          name: name,
          weight: weight,
          height: height,
          hairColor: hairColor,
          gpa: gpa,
        },
        success:function() {
          window.location.href = "/students/view";   
  },
      });
    }
  }
  