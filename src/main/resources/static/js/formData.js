const form = document.querySelector('form');
const firstName = document.querySelector('input[name = "firstName"]')
const lastName = document.querySelector('input[name= "lastName"]')
const birthDate = document.querySelector('input[name= "birthDate"]')
const email = document.querySelector('input[name= "email"]')
const genderField = document.getElementById('gender')
const genderFields = [
    document.getElementById("FEMALE"),
    document.getElementById("MALE"),
    document.getElementById("OTHER")
]
export {form, firstName, lastName, birthDate, email, genderField, genderFields}