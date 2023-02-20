package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@JmixEntity
@Entity(name = "enty_UsrGenFile")
@DiscriminatorValue(value="enty_UsrGenFile")
public class UsrGenFile extends UsrNode {


}