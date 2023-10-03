function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
        PF('vdatalist').clearFilters();
        PF('timer').stop(true);
        PF('timer').start();
    }
}

function closeDialog(dialog){
    PF(dialog).hide();
    PF('vdatalist').clearFilters();
    PF('timer').stop(true);
    PF('timer').start();
}

function timerReset() {
    PF('timer').stop(true);
    PF('timer').start();
}
