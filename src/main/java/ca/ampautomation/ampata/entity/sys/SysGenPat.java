package ca.ampautomation.ampata.entity.sys;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.MappedSuperclass;

@JmixEntity(name = "ampata_SysGenPat")
@MappedSuperclass
public class SysGenPat extends SysItem {
}