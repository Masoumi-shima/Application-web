import {form, firstName, lastName, birthDate, email, genderFields, genderField} from "./formData.js"

let inputs = [firstName, lastName, birthDate, email]
let areInputsValid = false

form.addEventListener('submit', (e) => {
    e.preventDefault()
    inputs.forEach(validateInputs)
    genderIsSelected()
    if (areInputsValid)
    {
        e.currentTarget.submit()
    }
});

inputs.forEach(input => {
    input.addEventListener('input', () => {
        validateInputs(input)
    });
})

genderFields.forEach(value => {
    value.addEventListener('change', () => {
        genderField.classList.remove('invalid')
        genderField.nextElementSibling.classList.add('hidden')
    })
})

function validateInputs(inputBox)
{
    inputBox.nextElementSibling.classList.add('hidden')
    areInputsValid = true

    if (!inputBox.value)
    {
        inputBox.classList.add('invalid');
        inputBox.nextElementSibling.classList.remove('hidden')
        areInputsValid = false
    }
}

function genderIsSelected()
{
    genderField.nextElementSibling.classList.add('hidden')
    if (document.querySelector('input[name="gender"]:checked') != null)
    {
        areInputsValid = true
    }
    else
    {
        genderField.nextElementSibling.classList.remove('hidden')
        areInputsValid = false
    }
}
