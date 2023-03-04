package ca.ampautomation.ampata.entity.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@JmixEntity
@Entity(name = "enty_UsrNodeGenFile")
@DiscriminatorValue(value="enty_UsrNodeGenFile")
public class UsrNodeGenFile extends UsrNodeBase {


}