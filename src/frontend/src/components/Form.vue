<template>
  <section>
    <form class="form-box" @submit.prevent="submitForm">

<!--      TODO: Form validation-->

      <h1>Fomulaire d'enregistrement</h1>

      <label for="firstName">Prénom</label>
      <input v-model="member.firstName" name="firstName" type="text">

      <label for="lastName">Nom</label>
      <input v-model="member.lastName" name="lastName" type="text">

      <label for="birthDate">Date de naissance</label>
      <input v-model="member.birthDate" name="birthDate" type="date">

      <label for="email">Adresse courriel</label>
      <input v-model="member.email" name="email" type="email">

      <div class="radio-box">
        <p>Genre</p>
        <div v-for="gender in genders" class="gender_values">
          <input v-model="member.gender" :value="gender" name="gender" type="radio">
          <label for="gender">{{gender}}</label>
        </div>
      </div>

      <div class="check-box">
        <input v-model="member.passedExam" name="passedExam" type="checkbox">
        <label for="passedExam">J'ai réussi l'examen de l'ordre.</label>
      </div>

      <Button type="submit" title="Enregistrer" />
      <Button type="reset" title="Réinitialiser" />

    </form>
  </section>
</template>

<script>
import MemberService from '@/services/MemberService'
import Button from '@/components/Button'
  export default {
    components: {Button},
    data() {
      return {
        //TODO: Get the gender values from the backend instead of hardcoding them
        genders: ["MALE", "FEMALE", "OTHER"],
        member: Object
      }
    },
    methods: {
      async getMember(permitNumber) {
        await MemberService.getAMember(permitNumber)
            .then((response) => this.member = response.data)
            .catch(e => {
              console.log(e)
            })
      },
      async submitForm() {
        const newMember = {
          firstName: this.member.firstName,
          lastName: this.member.lastName,
          birthDate: this.member.birthDate,
          email: this.member.email,
          gender: this.member.gender,
          passedExam: this.member.passedExam
        }
        if(this.$route.params.id === '') {
          await MemberService.createMember(newMember)
              .then(response => this.$router.push('/membre/' + response.data.permitNumber + '/confirmation'))
              .catch(e => {
                console.log(e)
              })
        }
        else {
          await MemberService.updateMember(this.$route.params.id, newMember)
              .then(response => this.$router.push('/membre/' + response.data.permitNumber + '/confirmation'))
              .catch(e => {
                console.log(e)
              })
        }
      }

    },
    created() {
      if(this.$route.params.id !== '') {
        this.getMember(this.$route.params.id)
      }
    }
  }
</script>

<style>
form {
  padding: 40px;
  font-size: 20px;
}

.form-box label {
  display: block;
  margin-bottom: 10px;
}

p {
  margin: 10px;
}

.check-box label {
  display: inline;
  margin-bottom: 10px;
  font-size: 15px;
  padding: 10px;
}

.form-box input {
  width: 300px;
  padding: 10px;
  font-size: 15px;
  color: #333;
  margin-bottom: 20px;
}

.gender_values {
  font-size: 15px;
  font-family: "Lato", sans-serif;
  background-image: linear-gradient(
      to right,
      rgb(0, 0, 0) 0%,
      rgb(50, 53, 56) 100%
  );
  color: aliceblue;
  display: flex;
  padding: 5px;
}

.radio-box input {
  width: auto;
}

.radio-box label {
  padding-left: 10px;
}

.check-box input {
  width: auto;
  margin-top: 30px;
}

button {
  font-size: 17px;
  padding: 10px;
  background: rgb(0, 128, 240);
  border: 1px solid rgb(0, 0, 0);
  border-radius: 10px;
  margin: 10px;
  transition: all 0.5s;
  cursor: pointer;
  color: aliceblue
}

button:hover {
  background-color: rgb(4, 56, 109);
  color: white;
}
</style>