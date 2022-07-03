const form = document.querySelector('form')
const firstName = document.querySelector('input[name = "firstName"]')
const lastName = document.querySelector('input[name= "lastName"]')
const birthDate = document.querySelector('input[name= "birthDate"]')
const email = document.querySelector('input[name= "email"]')
const gender = document.querySelector('input[name="gender"]:checked')
const passedExam = document.querySelector('input[name="passedExam"]')
const genderField = document.getElementById('gender')
const message = document.getElementById('successMsg')
const inputs = [firstName, lastName, birthDate, email]

message.classList.add('hidden')
let areInputsValid = false
let responseStatus;

form.addEventListener('submit', (e) => {
    e.preventDefault();
    // inputs.forEach(validateInputs)
    // genderIsSelected()
    renderServerResponse()
});

inputs.forEach(input => {
    input.addEventListener('input', () => {
    validateInputs(input)
    });
})

genderField.addEventListener('change',() => {
  genderIsSelected()
})

function validateInputs(inputBox)
{
    inputBox.classList.remove('invalid')
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
      return true
    }
    else
    {
      genderField.nextElementSibling.classList.remove('hidden')
      return null
    }
}

function getGender()
{
    if (document.querySelector('input[name="gender"]:checked') === null)
    {
        return null
    }
    else return document.querySelector('input[name="gender"]:checked').value
}

async function sendInformationToServer()
{
    let url = 'http://localhost:8080/api/v1/member'
    try {
      let res = await fetch(url,
        {
                    method: 'POST',
                    body: JSON.stringify(
                        {
                        "firstName": firstName.value,
                        "lastName": lastName.value,
                        "birthDate": birthDate.value,
                        "email": email.value,
                        "gender": getGender(),
                        "passedExam": passedExam.checked
                    }),
                    headers:
                        {
                      'Content-type': 'application/json'
                    }
                  });
      responseStatus = res.status
      return await res.json()
    }
    catch (error)
    {
      console.log(error)
    }
}

async function renderServerResponse()
{
    let serverResponse = await sendInformationToServer()
    if (responseStatus === 200)
    {
        form.remove()
        message.classList.remove('hidden')
        message.innerHTML += " Vos informations sont enregistrées!"
        document.getElementById("enteredFirstName").innerHTML += serverResponse.firstName
        document.getElementById("enteredLastName").innerHTML += serverResponse.lastName
        document.getElementById("enteredBirthDate").innerHTML += serverResponse.birthDate
        document.getElementById("enteredEmail").innerHTML += serverResponse.email
        document.getElementById("enteredGender").innerHTML += serverResponse.gender
    }

    else if (responseStatus === 400)
    {
        let invalidInput = serverResponse.field
        switch(true)
        {
            case invalidInput.includes("firstName") : validateInputs(firstName)
            case invalidInput.includes("lastName"): validateInputs(lastName)
            case invalidInput.includes("birthDate"): validateInputs(birthDate)
            case invalidInput.includes("email"): validateInputs(email)
            case invalidInput.includes("gender"): genderIsSelected()
        }
    }
}
