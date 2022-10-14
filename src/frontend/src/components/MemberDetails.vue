<template>
  <div class="container">
    <div v-if="currentMember">
      <h1>{{currentMember.firstName}} {{currentMember.lastName}}</h1>
      <p>Prénom : {{currentMember.firstName}}</p>
      <p>Nom : {{currentMember.lastName}}</p>
      <p>Date de naissance : {{currentMember.birthDate}}</p>
      <p>Adresse Courriel : {{currentMember.email}}</p>
      <p>Genre : {{currentMember.gender}}</p>
      <p>A réussi l'examen : {{currentMember.passedExam}}</p>
      <ButtonComponent class="btn-danger" title="Supprimer" @click="deleteMember(this.$route.params.id)"></ButtonComponent>
      <router-link :to="'/membres/modifier/' + this.$route.params.id">
        <ButtonComponent class="btn-primary" title="Modifier" />
      </router-link>
    </div>
  </div>
</template>

<script>
import MemberService from "@/services/MemberService";
import ButtonComponent from "@/components/ButtonComponent";

export default {
  name: 'MemberDetails',
  components: {ButtonComponent},
  data() {
    return {
      currentMember: null
    }
  },
  methods: {
    async getMember(permitNumber) {
      await MemberService.getAMember(permitNumber)
          .then((response) => {
            this.currentMember = response.data
          })
          .catch(e => {
            console.log(e)
          })
    },
    async deleteMember(permitNumber) {
      await MemberService.deleteMember(permitNumber)
          .catch(e => {
            console.log(e)
          })
      this.$router.push('/membres')

    }
  },
  created() {
    this.getMember(this.$route.params.id)
  },
  beforeRouteUpdate (to, from, next) {
    this.getMember(to.params.id)
    next()
  }
}
</script>
