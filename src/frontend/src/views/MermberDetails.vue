<template>
  <div class="details">
    <h1>Membre</h1>
    <p>Prénom: {{member.firstName}}</p>
    <p>Nom: {{member.lastName}}</p>
    <p>Date de naissance: {{member.birthDate}}</p>
    <p>Courriel: {{member.email}}</p>
    <p>Genre: {{member.gender}}</p>
  </div>

</template>

<script>

import MembersList from './MembersList';

export default {
  name: 'MemberDetails',
  data() {
    return {
      member: Object
    }
  },
  // created() {
  //   MembersList.getAMember()
  // }
}
</script>
<style scoped>
  .details {
    margin-right: 0;
    alignment: center;
  }
  h1 {
    margin-right: 0;
    align-content: center;
  }
</style>