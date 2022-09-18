<template>
  <div class="container">
    <div class="row my-5 mx-0">
      <div class="col-auto">
        <h1>Formulaire d'enregistrement</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-md-8">
        <form class="needs-validation" @submit.prevent="submitForm">

          <div class="row mb-5">
            <label for="firstName" class="col-sm-2 col-form-label">Prénom</label>
            <div class="col-sm-10">
              <input v-model="member.firstName" class="form-control" name="firstName" type="text">
              <div class="invalid-feedback">
                Ce champs est obligatoire.
              </div>
            </div>
          </div>

          <div class="row mb-5">
            <label for="lastName" class="col-sm-2 col-form-label">Nom</label>
            <div class="col-sm-10">
              <input v-model="member.lastName" class="form-control" name="lastName" type="text">
              <div class="invalid-feedback">
                Ce champs est obligatoire.
              </div>
            </div>
          </div>

          <div class="row mb-5">
            <label for="birthDate" class="col-sm-2 col-form-label">Date de naissance</label>
            <div class="col-sm-10">
              <input v-model="member.birthDate" class="form-control" name="birthDate" type="date">
              <div class="invalid-feedback">
                Ce champs est obligatoire.
              </div>
            </div>
          </div>

          <div class="row mb-5">
            <label for="email" class="col-sm-2 col-form-label">Adresse courriel</label>
            <div class="col-sm-10">
              <input v-model="member.email" class="form-control" name="email" type="email">
              <div class="invalid-feedback">
                Ce champs est obligatoire.
              </div>
            </div>
          </div>

          <fieldset class="row mb-5">
            <legend class="col-form-label col-sm-2 pt-0">Genre</legend>
            <div class="col-sm-10">
              <div v-for="gender in genders" class="form-check">
                <input v-model="member.gender" class="form-check-input" :value="gender" name="gender" type="radio">
                <label for="gender">{{gender}}</label>
                <div class="invalid-feedback">
                  Choisissez une option.
                </div>
              </div>
            </div>
          </fieldset>

           <div class="row mb-5">
             <div class="col-sm-10">
               <div class="form-check">
                 <input v-model="member.passedExam" class="form-check-input" name="passedExam" type="checkbox">
                 <label for="passedExam" class="form-check-label">J'ai réussi l'examen de l'ordre.</label>
               </div>
             </div>
           </div>

          <ButtonComponent type="submit" class="btn-primary" title="Enregistrer" />
          <ButtonComponent type="reset" class="btn-primary" title="Réinitialiser" />

        </form>

      </div>
    </div>
  </div>
</template>

<script>
import MemberService from '@/services/MemberService'
import ButtonComponent from "@/components/ButtonComponent";
  export default {
    components: {ButtonComponent},
    data() {
      return {
        //TODO: Get the gender values from the backend instead of hardcoding them
        genders: ["MALE", "FEMALE", "OTHER"],
        member: {
          firstName: '',
          lastName: '',
          email: '',
          gender: null,
          passedExam: null
        }
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