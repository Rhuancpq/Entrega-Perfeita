package com.oofga.ep.entregaperfeita;

import com.oofga.ep.entregaperfeita.ActionFragment;
import com.oofga.ep.entregaperfeita.RecordFragment;
import com.oofga.ep.entregaperfeita.RegisterFragment;
import com.oofga.ep.entregaperfeita.RemoveFragment;
import com.oofga.ep.entregaperfeita.SelectionFragment;
import com.oofga.ep.entregaperfeita.SettingsFragment;

public interface FragmentListener extends SettingsFragment.SettingsListener, ActionFragment.ActionListener,
        RegisterFragment.RegisterListener, SelectionFragment.SelectionListener,
        RecordFragment.RecordListener, RemoveFragment.RemoveListener{

}
