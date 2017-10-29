(function() {
    'use strict';

    angular
        .module('kickApp')
        .controller('StoryTypeDialogController', StoryTypeDialogController);

    StoryTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'StoryType', 'Property'];

    function StoryTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, StoryType, Property) {
        var vm = this;

        vm.storyType = entity;
        vm.clear = clear;
        vm.save = save;
        vm.properties = Property.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.storyType.id !== null) {
                StoryType.update(vm.storyType, onSaveSuccess, onSaveError);
            } else {
                StoryType.save(vm.storyType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kickApp:storyTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
