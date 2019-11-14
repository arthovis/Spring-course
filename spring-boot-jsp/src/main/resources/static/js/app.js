function validate() {
    var name = document.getElementById("name").value;
    if (name != '') {
        return true;
    } else {
    alert('Please enter a valid name.');
            return false;
    }
}